package com.bruce.c_016;

import java.util.concurrent.TimeUnit;

/**
 * synchronized优化
 * 锁定代码块越小越好
 *
 * @author: Chen Kj
 * @date: 2019/6/13 16:14
 * @version: 1.0
 */
public class T {
    int count = 0;

    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 业务中只有这句需要sync, 不应该给整个方法上锁.
        // 采用细粒度的锁，可以使线程征用时间变短.
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
