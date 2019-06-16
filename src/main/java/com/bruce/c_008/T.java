package com.bruce.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的话依然可以获取锁
 * 也就是说synchronized获取锁是可重入的
 * @author: Chen Kj
 * @date: 2019/6/13 14:09
 * @version: 1.0
 */
public class T {
    synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        System.out.println("m2 start");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
