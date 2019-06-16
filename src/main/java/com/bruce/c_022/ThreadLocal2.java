package com.bruce.c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * @author: Chen Kj
 * @date: 2019/6/14 13:54
 * @version: 1.0
 */
public class ThreadLocal2 {


    static ThreadLocal<Person> local = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(local.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            local.set(new Person("lina"));
        }).start();
    }


    static class Person {
        String name = "张三";

        public Person(String name) {
            this.name = name;
        }
    }

}

