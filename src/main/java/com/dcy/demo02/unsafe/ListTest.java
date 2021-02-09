package com.dcy.demo02.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author：dcy
 * @Description: java.util.ConcurrentModificationException    并非修改异常，是不安全的集合就会有
 * @Date: 2021/1/15 8:20
 */
public class ListTest {

    public static void main(String[] args) {
        /**
         * 并发下 ArrayList 不安全
         * 解决方案：
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */

        //CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
