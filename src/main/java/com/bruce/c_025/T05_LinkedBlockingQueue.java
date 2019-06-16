package com.bruce.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 无界的
 * @author: Chen Kj
 * @date: 2019/6/14 16:38
 * @version: 1.0
 */
public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue();

    static Random random = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // 如果满了就会等待
                    blockingQueue.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    // 如果队列中空了，进入阻塞
                    try {
                        System.out.println(Thread.currentThread().getName() + " take - " + blockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
