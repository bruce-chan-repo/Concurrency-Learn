package com.bruce.c_011;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，则默认会释放锁
 *
 * @author: Chen Kj
 * @date: 2019/6/13 14:25
 * @version: 1.0
 */
public class T {
    int count = 0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start...");
        while (true) {
            count++;
            System.out.println("count " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                // 此处抛出异常，锁将被释放
                int i = 1 / 0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "Thread1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m, "Thread2").start();
    }
}
