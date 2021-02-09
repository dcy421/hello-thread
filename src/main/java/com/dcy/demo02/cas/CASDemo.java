package com.dcy.demo02.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：dcy
 * @Description: CAS
 * @Date: 2021/1/21 13:57
 */
public class CASDemo {


    /**
     * CAS  compareAndSet：比较并交换！
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 期望，更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望的值达到了，那么就更新，否则，就不更新
        boolean b = atomicInteger.compareAndSet(2020, 2021);
        System.out.println(b);
        System.out.println(atomicInteger.get());


        boolean b1 = atomicInteger.compareAndSet(2020, 2021);
        System.out.println(b1);
        System.out.println(atomicInteger.get());
    }

}
