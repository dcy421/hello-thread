package com.dcy.demo01.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * 线程不安全的集合
 * @Date: 2021/1/12 13:34
 */
public class UnsafeList {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(100);
        System.out.println(list.size());
    }
}
