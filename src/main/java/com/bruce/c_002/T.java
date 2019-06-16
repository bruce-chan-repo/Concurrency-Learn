package com.bruce.c_002;

/**
 * synchronized 关键字
 * 对某个对象加锁
 * @author: Chen Kj
 * @date: 2019/6/13 11:27
 * @version: 1.0
 */
public class T {
    private int count = 10;


    public void m() {
        // 要执行下面的代码，要先获取当前类实例对象的锁
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName());
        }
    }
}
