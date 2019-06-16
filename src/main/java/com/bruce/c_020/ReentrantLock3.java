package com.bruce.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用ReentrantLock可以同样实现
 * 但是需要手动释放锁
 * 使用synchronized出现异常时，JVM会自动释放锁.
 *
 * 可以使用tryLock尝试获取锁
 * @author: Chen Kj
 * @date: 2019/6/13 17:53
 * @version: 1.0
 */
public class ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("m1");

                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        boolean locked = false;

        try {
            lock.lock();
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            System.out.println("m2... " + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        // 会阻塞
        /*lock.lock();
        System.out.println("m2 ...");
        lock.unlock();*/
    }

    public static void main(String[] args) {
        ReentrantLock3 r2 = new ReentrantLock3();

        new Thread(r2::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r2::m2).start();
    }
}
