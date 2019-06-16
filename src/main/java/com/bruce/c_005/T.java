package com.bruce.c_005;

/**
 *
 * @author: Chen Kj
 * @date: 2019/6/13 11:50
 * @version: 1.0
 */
public class T implements Runnable {

    private int count = 10;

    @Override
    public void run() {
        count--;
        System.out.println("Thread: " + Thread.currentThread().getName() + " Count: " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread." + i).start();
        }
    }
}
