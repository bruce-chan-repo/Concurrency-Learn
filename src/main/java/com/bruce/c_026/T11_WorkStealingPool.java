package com.bruce.c_026;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取
 * @author: Chen Kj
 * @date: 2019/6/16 21:34
 * @version: 1.0
 */
public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newWorkStealingPool();

        // daemon(守护)线程
        executorService.execute(new R(1000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));

        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}
