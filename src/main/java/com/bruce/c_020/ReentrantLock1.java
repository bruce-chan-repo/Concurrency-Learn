package com.bruce.c_020;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 * @author: Chen Kj
 * @date: 2019/6/13 17:42
 * @version: 1.0
 */
public class ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("m1");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        ReentrantLock1 r1 = new ReentrantLock1();

        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}