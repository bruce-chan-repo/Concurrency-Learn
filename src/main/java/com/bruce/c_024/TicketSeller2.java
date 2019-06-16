package com.bruce.c_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 *
 * @author: Chen Kj
 * @date: 2019/6/14 14:33
 * @version: 1.0
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("车票No." + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("出售 " + tickets.remove(0));
                }
            }).start();
        }
    }
}
