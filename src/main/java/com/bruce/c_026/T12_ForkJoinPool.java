package com.bruce.c_026;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/16 21:46
 * @version: 1.0
 */
public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println(Arrays.stream(nums).sum()); //stream api
    }


    static class AddTask extends RecursiveTask<Long> {
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }

            // 计算中间值
            int middle = start + (end - start) / 2;

            AddTask addTask1 = new AddTask(start, middle);
            AddTask addTask2 = new AddTask(middle, end);
            addTask1.fork();
            addTask2.fork();

            return addTask1.join() + addTask2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        AddTask task = new AddTask(0, nums.length);
        forkJoinPool.execute(task);

        Long result = task.join();
        System.out.println(result);
    }
}
