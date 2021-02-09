package com.dcy.demo02.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：dcy
 * @Description:
 * java.util.ConcurrentModificationException  并非修改异常，是不安全的集合就会有
 * @Date: 2021/1/15 8:38
 */
public class MapTest {

    public static void main(String[] args) {
        /**
         * int initialCapacity：初始化容量
         * float loadFactor：加载因子
         * 1、Collections.synchronizedSortedMap()
         * 2、new ConcurrentHashMap<>();
         *
         */

        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
