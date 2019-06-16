package com.bruce.c_012;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，解决多线程变量的可见性问题。
 * 当使用volatile修饰变量时，意味着这个变量的操作都是在内存上，不会产生副本。这就保证了可见性问题
 * 局部阻止了指令重排
 * 它只能解决可见性问题，当多线程访问变量的时候，并不能保证数据的一致性
 * @author: Chen Kj
 * @date: 2019/6/13 14:32
 * @version: 1.0
 */
public class T {
    volatile boolean running = true;

    void m() {
        System.out.println("m start..");

        while (running) {
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
