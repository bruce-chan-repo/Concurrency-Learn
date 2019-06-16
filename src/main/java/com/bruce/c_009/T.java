package com.bruce.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，当线程拥有某个对象的锁，依然可以再次获取该对象的锁
 * 也就是说synchronized获取的锁是可重入的
 * 这里继承中有可能发生的情形，子类调用父类的同步方法
 * @author: Chen Kj
 * @date: 2019/6/13 14:14
 * @version: 1.0
 */
public class T {
    synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void m2() {
        System.out.println("m2 start");
        /*try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        TT t = new TT();
        new Thread(t::m).start();
    }
}

class TT extends T {
    @Override
    synchronized void m() {
        System.out.println("child m start...");
        super.m2();
        System.out.println("child m end... ");
    }
}