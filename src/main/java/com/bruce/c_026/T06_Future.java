package com.bruce.c_026;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 18:25
 * @version: 1.0
 */
public class T06_Future {
    public static void main(String[] args) {
        Future<Integer> task = new FutureTask<Integer>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
    }
}
