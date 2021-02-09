package com.dcy.demo02.add;

import java.util.concurrent.CountDownLatch;

/**
 * @Author：dcy
 * @Description: 计数器
 * 类似于减法计数器
 * @Date: 2021/1/15 9:00
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread( () ->{
                System.out.println(Thread.currentThread().getName()+"Go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        // 等待计数器归零，然后在向下执行
        countDownLatch.await();

        System.out.println("关门");
    }
}
