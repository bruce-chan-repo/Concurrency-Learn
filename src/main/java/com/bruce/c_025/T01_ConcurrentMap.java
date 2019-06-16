package com.bruce.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 15:06
 * @version: 1.0
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
        //Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>();

        //Map<String, String> map = new ConcurrentHashMap<>();
        // 跳表，有顺序的。
        Map<String, String> map = new ConcurrentSkipListMap<>();

        Random random = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    map.put("ABC" + random.nextInt(10000), "ABC" + random.nextInt(10000));
                    countDownLatch.countDown();
                }
            });
        }

        Arrays.asList(threads).forEach(t -> t.start());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗時:" + (end - start));
    }
}
