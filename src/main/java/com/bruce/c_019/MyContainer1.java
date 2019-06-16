package com.bruce.c_019;

import oracle.jrockit.jfr.jdkevents.throwabletransform.ConstructorTracerWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 分析下面这个程序，能完成这个功能吗？
 *
 * @author: Chen Kj
 * @date: 2019/6/13 16:58
 * @version: 1.0
 */
public class MyContainer1 {
    List elements = new ArrayList();

    public void add(Object o) {
        elements.add(o);
    }

    public int size() {
        return elements.size();
    }

    public static void main(String[] args) {
        MyContainer1 myContainer1 = new MyContainer1();

        // 线程1添加元素
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myContainer1.add(i);
                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (myContainer1.size() == 5) {
                    break;
                }
            }
            System.out.println("t2结束");
        }, "t2").start();
    }
}
