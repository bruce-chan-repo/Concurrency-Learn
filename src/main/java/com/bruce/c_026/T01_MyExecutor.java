package com.bruce.c_026;

import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 17:48
 * @version: 1.0
 */
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        T01_MyExecutor executor = new T01_MyExecutor();
        executor.execute(() -> System.out.println("Hello Executor"));
    }
}
