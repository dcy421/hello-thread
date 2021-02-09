package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description:
 * 发下问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱。
 * @Date: 2021/1/11 15:31
 */
public class TestThread5 implements Runnable {

    private int ticketNums = 10;

    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -->拿到了" + ticketNums-- + "票");
        }
    }

    public static void main(String[] args) {
        TestThread5 ticket = new TestThread5();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "黄牛").start();
    }
}
