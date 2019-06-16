package com.bruce.c_001;

/**
 * synchronized 关键字
 * 对某个对象加锁
 * @author: Chen Kj
 * @date: 2019/6/13 10:43
 * @version: 1.0
 */
public class T {

    private int count = 10;
    private Object lock = new Object();


    public void m() {
        // 任何线程想执行下面代码，都需要拿到lock对象的锁
        synchronized (lock) {
            count--;
            System.out.println(Thread.currentThread().getName());
            System.out.println(count);
        }
    }
}
