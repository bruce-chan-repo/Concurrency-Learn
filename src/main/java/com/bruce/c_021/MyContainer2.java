package com.bruce.c_021;

import com.sun.deploy.association.utility.WinRegistryUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * @author: Chen Kj
 * @date: 2019/6/14 11:11
 * @version: 1.0
 */
public class MyContainer2<T> {
    private final List<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();


    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            count++;
            // 通知所有消费者进行消费
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        try {
            T t = null;
            lock.lock();
            while (list.size() == 0) {
               consumer.await();
            }

            t = list.remove(0);
            count--;
            // 唤醒生产者进行生产
            producer.signalAll();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public static void main(String[] args) {
        MyContainer2<String> container = new MyContainer2<>();

        // 启动消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("消费消息【" + container.get() + "】");
                                                                      }
            }, "consumer" +  i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动两个生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put("" + j);
                    System.out.println("生产消息【" + j + "】");
                }
            }, "consumer" +  i).start();
        }
    }
}
