package com.dcy.demo01.gaoji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：dcy
 * @Description: 测试线程池
 * @Date: 2021/1/13 8:49
 */
public class TestPool {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());

        executorService.shutdownNow();
    }

}


class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}