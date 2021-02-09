package com.dcy.demo03.cf;

import java.util.concurrent.*;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/21 14:37
 */
public class CompletableFutureTest {

    /**
     * 创建线程池
     */
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main..........start............");
        /**
         * 启动异步任务
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);

        /**
         * 启动异步任务，带返回值
         * 方法完成后的感知
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

        /**
         * 方法执行完成后的处理
         */
        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((res, throwable) -> {
            if (res != null){
                return res * 2;
            }
            if (throwable != null){
                return 0;
            }
            System.out.println("异步任务成功完成了....结果是：" + res + "；异常是：" + throwable);
            return 0;
        });
        System.out.println(future1.get());*/

        /**
         * 线程串行化
         * 1、thenRun：不能获取上一步的执行结果
         * 2、thenAcceptAsync：能接受上一步结果，但是无返回值
         * 3、thenApplyAsync：能接受上一步的结果，有返回值
         */
        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了" + res);
            return res * 2;
        });

        // get 阻塞方法
        System.out.println(future1.get());*/

        /**
         * 两个都完成
         * 1、runAfterBothAsync：不能感知异步任务的结果
         * 2、thenAcceptBothAsync: 能感知到返回值
         * 3、thenCombineAsync：能感知到返回值 ，还能返回数据
         */
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
        }, executor);
        System.out.println(stringCompletableFuture.get());*/


        /**
         * 全部执行
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "hello.jpg";
        },executor);
        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性");
            return "黑色+256G";
        },executor);
        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品介绍");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为";
        },executor);

//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);

        // 等待所有完成
//        allOf.get();
        anyOf.get();

        System.out.println("main..........end............");
    }

}
