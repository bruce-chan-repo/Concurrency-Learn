package com.bruce.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 * @author: Chen Kj
 * @date: 2019/6/14 15:26
 * @version: 1.0
 */
public class T01_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        Random random = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                list.add("--" + random.nextInt(10000));
            });
        }

        long start = System.currentTimeMillis();

        Arrays.asList(threads).forEach(t -> t.start());
        Arrays.asList(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();

        System.out.println("耗时:" + (end - start));
    }
}
