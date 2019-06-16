package com.bruce.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author: Chen Kj
 * @date: 2019/6/14 15:39
 * @version: 1.0
 */
public class T01_SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> strings = Collections.synchronizedList(list);
    }
}
