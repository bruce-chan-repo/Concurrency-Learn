package com.bruce.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 * 使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 *
 * ReentrantLock还可以指定为公平锁
 * @author: Chen Kj
 * @date: 2019/6/14 10:14
 * @version: 1.0
 */
public class ReentrantLock5 extends Thread {
    private Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取到锁, i = " + i);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 lock = new ReentrantLock5();

        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);

        t1.start();
        t2.start();
    }
}