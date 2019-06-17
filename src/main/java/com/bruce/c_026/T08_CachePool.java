package com.bruce.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 弹性的线程池，60秒后自动释放.
 * 当有新的任务时，自动创建线程
 * @author: Chen Kj
 * @date: 2019/6/16 21:08
 * @version: 1.0
 */
public class T08_CachePool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(70);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);
    }
}
