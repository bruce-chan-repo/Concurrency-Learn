package com.bruce.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0
 * ConcurrentLinkedQueue/ConcurrentArrayQueue
 * BlockingQueue
 *      LinkedBlockingQueue, ArrayBlockingQueue
 *      DelayQueue,
 *      SynchronousQueue,
 *      LinkedTransferQueue
 * @author: Chen Kj
 * @date: 2019/6/14 17:36
 * @version: 1.0
 */
public class T09_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                //
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 阻塞等待消费者消费, 底层也是transfer
        //strs.put("aaaa");
        strs.add("aaa");

        System.out.println(strs.size());
    }
}
