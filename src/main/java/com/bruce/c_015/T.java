package com.bruce.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 比较上一个小程序，使用synchronized能同时解决原子性和可见性
 * volatile只能保证可见性
 * @author: Chen Kj
 * @date: 2019/6/13 15:02
 * @version: 1.0
 */
public class T {
    AtomicInteger count = new AtomicInteger();

    void m() {
        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "Thread" + i));
        }

        // 启动线程
        threads.forEach(thread -> thread.start());

        // 等待终止线程
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
