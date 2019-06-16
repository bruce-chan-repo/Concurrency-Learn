package com.bruce.c_017;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，修改o属性，不会影响锁的使用
 * 如果将引用改变为新的对象，则锁定的对象发生改变
 * @author: Chen Kj
 * @date: 2019/6/13 16:27
 * @version: 1.0
 */
public class T {
    Object lock = new Object();


    void m() {
        synchronized (lock) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("CurrThread: " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        // 创建第一个线程，t::m方法引用.
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 创建第二个线程
        Thread thread = new Thread(t::m, "t2");

        // 修改锁对象, 如果不修改锁对象，t2线程将永远的不到运行
        //t.lock = new Object();
        thread.start();
    }

}
