package com.bruce.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile并不能解决多线程访问同一变量的一致性问题
 * 也就是volatile不能替代synchronized
 *
 * @author: Chen Kj
 * @date: 2019/6/13 14:51
 * @version: 1.0
 */
public class T {
    /*volatile*/ int count = 0;

    synchronized void m() {
        for (int i = 0; i < 100; i++) {
            count++;
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
