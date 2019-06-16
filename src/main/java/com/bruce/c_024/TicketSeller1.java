package com.bruce.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * @author: Chen Kj
 * @date: 2019/6/14 14:28
 * @version: 1.0
 */
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();
    
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("车票No." + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("出售 " + tickets.remove(0));
                }
            }).start();
        }
    }
}
