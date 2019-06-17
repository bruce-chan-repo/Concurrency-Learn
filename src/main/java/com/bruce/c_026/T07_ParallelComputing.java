package com.bruce.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 固定线程池
 * 不会销毁
 *
 * @author: Chen Kj
 * @date: 2019/6/16 20:50
 * @version: 1.0
 */
public class T07_ParallelComputing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(0, 20000);
        long end = System.currentTimeMillis();

        System.out.println(end - start);


        final int cpuCoreNum = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(0, 7000);
        MyTask t2 = new MyTask(7000, 12000);
        MyTask t3 = new MyTask(12000, 16000);
        MyTask t4 = new MyTask(16000, 20000);

        Future<List<Integer>> f1 = executorService.submit(t1);
        Future<List<Integer>> f2 = executorService.submit(t1);
        Future<List<Integer>> f3 = executorService.submit(t1);
        Future<List<Integer>> f4 = executorService.submit(t1);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            // 获取质数
            List<Integer> primes = getPrime(startPos, endPos);
            return primes;
        }
    }


    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            if(isPrime(i)) results.add(i);
        }

        return results;
    }
}
