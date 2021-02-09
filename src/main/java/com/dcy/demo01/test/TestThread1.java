package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/11 15:31
 */
public class TestThread1 extends Thread {

    @Override
    public void run() {
        System.out.println("启动线程"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        System.out.println("主线程begin");
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();
        System.out.println("主线程end");
    }
}
