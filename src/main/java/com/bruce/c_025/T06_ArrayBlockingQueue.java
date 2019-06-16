package com.bruce.c_025;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 16:47
 * @version: 1.0
 */
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        // 满了就会阻塞
        //strs.put("aaa");
        // 满了会抛异常
        //strs.add("aaa");
        // 会将添加结果返回
        //boolean isPuted = strs.offer("aaaa");
        // 带等待时间的offer
        boolean isPuted = strs.offer("aaa", 2, TimeUnit.SECONDS);
        System.out.println(isPuted);
        System.out.println(strs);
    }
}

