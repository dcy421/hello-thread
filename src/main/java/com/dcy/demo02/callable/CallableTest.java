package com.dcy.demo02.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/15 8:48
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 怎么启动Callable 接口

        FutureTask<String> target = new FutureTask<>(new MyThread());
        new Thread(target, "A").start();
        new Thread(target, "B").start();// 结果会被缓存，所以调用一次
        // 获取callable的返回结果
        // 这个get方法可能会产生阻塞，正常情况把他放到最后一行
        String s = target.get();
        System.out.println(s);
    }
}

class MyThread implements Callable<String> {

    /**
     * 线程返回值
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }
}