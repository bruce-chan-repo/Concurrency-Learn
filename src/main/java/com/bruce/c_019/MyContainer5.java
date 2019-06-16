package com.bruce.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/13 17:15
 * @version: 1.0
 */
public class MyContainer5 {
    volatile List elements = new ArrayList();

    public void add(Object o) {
        elements.add(o);
    }

    public int size() {
        return elements.size();
    }

    public static void main(String[] args) {
        MyContainer5 myContainer3 = new MyContainer5();
        // 使用计数门闩
        CountDownLatch countDownLatch = new CountDownLatch(1);


        // 启动监听线程
        new Thread(() -> {
            System.out.println("t2 start");
                if (myContainer3.size() != 5) {
                    try {
                        // 进入阻塞
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
        }, "t2").start();

        // 启动添加元素的线程
        new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                myContainer3.add(i);
                System.out.println("add " + i);

                if (myContainer3.size() == 5) {
                    // 当计数为0的时候，将放开门闩让t2执行
                    countDownLatch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}
