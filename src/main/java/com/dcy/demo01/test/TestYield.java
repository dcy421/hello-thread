package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description: 测试礼让线程
 * 例如不一定成功，看cpu心情
 * @Date: 2021/1/12 9:44
 */
public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }
}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        // 线程礼让
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束执行");
    }
}
