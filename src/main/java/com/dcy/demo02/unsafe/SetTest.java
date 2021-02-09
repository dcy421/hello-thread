package com.dcy.demo02.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author：dcy
 * @Description:
 * java.util.ConcurrentModificationException 并发修改异常
 * @Date: 2021/1/15 8:32
 */
public class SetTest {

    public static void main(String[] args) {

        /**
         * 解决办法
         * 1、Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2、Set<String> set = new CopyOnWriteArraySet<>();
         */

        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
