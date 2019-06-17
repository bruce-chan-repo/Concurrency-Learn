package com.bruce.c_026;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 只开启一个线程，重复使用
 * 只执行一个任务，别的进入到队列, FIFO
 * @author: Chen Kj
 * @date: 2019/6/16 21:20
 * @version: 1.0
 */
public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;

            executorService.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

        System.out.println(executorService);
    }
}
