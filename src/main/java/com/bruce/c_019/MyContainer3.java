package com.bruce.c_019;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/13 17:15
 * @version: 1.0
 */
public class MyContainer3 {
    volatile List elements = new ArrayList();

    public void add(Object o) {
        elements.add(o);
    }

    public int size() {
        return elements.size();
    }

    public static void main(String[] args) {
        MyContainer3 myContainer3 = new MyContainer3();
        final Object lock = new Object();

        // 启动监听线程
        new Thread(() -> {
            System.out.println("t2 start");
            synchronized (lock) {
                if (myContainer3.size() != 5) {
                    try {
                        // 调用锁的wait()，会释放锁. 使当前线程进入到阻塞状态。
                        // 直到别的线程使用notify或notifyAll唤醒他.
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
            }
        }, "t2").start();

        // 启动添加元素的线程
        new Thread(() -> {
            System.out.println("t1 start");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    myContainer3.add(i);
                    System.out.println("add " + i);

                    if (myContainer3.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
