package com.dcy.demo02.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author：dcy
 * @Description: 加法计数器
 * @Date: 2021/1/15 9:05
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐七颗龙之召唤神龙
         */
        // 召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });
        for (int i = 1; i <= 7; i++) {
            final int finalI = i;
            new Thread(() -> {
                // lambda 中拿取for中的i变量，必须变成final值
                System.out.println(Thread.currentThread().getName() + "收集" + finalI + "个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }


    }
}
