package com.dcy.demo04;

import java.util.concurrent.*;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/20 8:43
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        threadPoolInit();
        System.out.println("硬件核心数：" + Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        // 一池5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池1线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 一池N线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
