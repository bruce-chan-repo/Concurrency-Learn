package com.bruce.c_018;

import java.util.concurrent.TimeUnit;

/**
 * 不要以字符串常量作为锁对象
 * 下面例子m1和m2锁定的其实一个对象
 * 这种情况还会发生诡异现象，比如你使用一个类库，里面使用了字符串"Hello"为锁
 * 但是你在不知道的情况下，也使用了"Hello"作为锁。这就会发送非常诡异的死锁阻塞.
 * @author: Chen Kj
 * @date: 2019/6/13 16:40
 * @version: 1.0
 */
public class T {
    String lock1 = "hello";
    String lock2 = "hello";

    void m1() {
        synchronized (lock1) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("m1");
            }
        }
    }

    void m2() {
        // 获取不到锁
        synchronized (lock2) {
            System.out.println("m2");
        }
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
