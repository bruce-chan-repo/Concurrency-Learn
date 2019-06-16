package com.bruce.c_007;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法和非同步方法是否能够同时调用?
 * @author: Chen Kj
 * @date: 2019/6/13 12:37
 * @version: 1.0
 */
public class T {

    public synchronized void m1() {
        System.out.println("m1 start...");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }

    public void m2() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("m2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m1, "Thread1").start();
        new Thread(t::m2, "Thread2").start();
    }

}
