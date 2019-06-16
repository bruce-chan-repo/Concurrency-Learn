package com.bruce.c_023;

import java.util.Arrays;

/**
 * 线程安全的Singleton
 * 不用加锁，也能实现懒加载
 * 也可以使用double check实现. 但是效率没有这个高
 * @author: Chen Kj
 * @date: 2019/6/14 14:03
 * @version: 1.0
 */
public class Singleton {
    private Singleton() {

    }

    public static Singleton getInstace() {
        return Inner.singleton;
    }

    private static class Inner {
        private static Singleton singleton = new Singleton();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Singleton.getInstace());
            });
        }

        Arrays.asList(threads).forEach(t -> t.start());
    }
}
