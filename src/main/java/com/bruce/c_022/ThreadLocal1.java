package com.bruce.c_022;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author: Chen Kj
 * @date: 2019/6/14 13:47
 * @version: 1.0
 */
public class ThreadLocal1 {
    static Person person = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person.name);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "李四";
        }, "t2").start();
    }
}

class Person {
    String name = "张三";
}
