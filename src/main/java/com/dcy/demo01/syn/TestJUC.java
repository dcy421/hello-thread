package com.dcy.demo01.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author：dcy
 * @Description: 测试JUC安全类型的集合
 * @Date: 2021/1/12 14:26
 */
public class TestJUC {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
    }
}
