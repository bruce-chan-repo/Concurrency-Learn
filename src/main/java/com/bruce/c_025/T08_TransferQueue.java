package com.bruce.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 17:11
 * @version: 1.0
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        //strs.put("aaa");
        new Thread(() -> {
            try {
                // 如果队列为空，将陷入阻塞
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 直接转给消费者
        strs.transfer("aaa");
    }
}
