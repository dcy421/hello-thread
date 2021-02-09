package com.dcy.demo03.cf;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/21 14:37
 */
public class CompletableFutureTest1 {

    /**
     * 创建线程池
     */
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static ExecutorService executor2 = Executors.newSingleThreadExecutor();
    public static ExecutorService executor1 = Executors.newCachedThreadPool();
    public static ExecutorService executor3 = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main..........start............");
        /**
         * 启动异步任务，无返回值
         */
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);

        *//**
         * 启动异步任务，有返回值
         *//*
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor);

        // 此方法是阻塞的
        future1.get();*/

        System.out.println("计算结果完成时的回调方法");
        /**
         * 计算结果完成时的回调方法
         */
        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((res, exception) -> {
            // 虽然能得到异常信息，但是没法修改返回数据
            System.out.println("异步任务成功完成了....结果是：" + res + "；异常是：" + exception);
        }).exceptionally((exception) -> {
            // 可以感知异常，同时返回默认值
            return 10;
        });*/


        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了" + res);
            return res * 2;
        });*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenAcceptAsync(res -> {
            System.out.println("任务2启动了" + res);
        },executor);*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenRunAsync(() -> {
            System.out.println("任务2启动了");
        },executor);*/

        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return "Hello";
        }, executor);

        CompletableFuture<String> stringCompletableFuture = future01.thenCombineAsync(future02, (f1, f2) -> {
            System.out.println("任务3开始。。。之前的结果" + f1 + "-->" + f2);
            return "333";
        }, executor);*/


        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return "Hello";
        }, executor);

        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
            System.out.println("任务3开始。。。之前的结果" + f1 + "-->" + f2);
        }, executor);*/


        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return 2222;
        }, executor);

        future01.applyToEitherAsync(future02,integer -> {
            System.out.println(integer);
            return 2;
        }, executor);*/

        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return 444;
        }, executor);

        future01.acceptEitherAsync(future02,integer -> {
            System.out.println(integer);
        }, executor);*/


        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return 444;
        }, executor);

        future01.runAfterEitherAsync(future02,() -> {
            System.out.println("上面有一个已经完成了。");
        }, executor);*/

        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务一运行结果：" + i);
            return i;
        }, executor);

        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return 444;
        }, executor);

        future01.runAfterBothAsync(future02,() -> {
            System.out.println("上面两个任务都执行完成了。");
        }, executor);*/


        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程：" + Thread.currentThread().getId());
            System.out.println("任务二运行");
            return 444;
        }, executor);

        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1=" + t);
            return t;
        }).thenCombineAsync(future, (res, res2) -> {
            return res + res2;
        }, executor);
        System.out.println("thenCompose result : "+f.get());*/

        /*CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "hello.jpg";
        }, executor);
        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性");
            return "黑色+256G";
        }, executor);
        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品介绍");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为";
        }, executor);
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get();*/

        System.out.println("main..........end............");
    }

}
