package com.dcy.demo01.test;

/**
 * @Author：dcy
 * @Description:
 * 模拟网络延时
 * @Date: 2021/1/12 9:38
 */
public class TestSleep implements Runnable {

    private int ticketNums = 10;

    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            /*try {
                // 模拟延时
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName() + " -->拿到了" + ticketNums-- + "票");
        }
    }

    public static void main(String[] args) {
        TestSleep ticket = new TestSleep();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "老师").start();
        new Thread(ticket, "黄牛").start();
    }
}
