package com.bruce.c_004;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/13 11:39
 * @version: 1.0
 */
public class T {

    private static int count = 10;


    /**
     * 这里等同于 synchronized(T.class)
     * 锁定的是类对象
     */
    public synchronized static void m1() {
        count--;
        System.out.println(Thread.currentThread().getName());
    }

    public static void m2() {
        synchronized (T.class) {
            count--;
        }
    }
}
