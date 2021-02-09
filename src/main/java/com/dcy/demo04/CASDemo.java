package com.dcy.demo04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/8 8:15
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // main do thing...
        System.out.println(atomicInteger.compareAndSet(5, 2019) + " \t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + " \t current data:" + atomicInteger.get());
    }
}
