package com.dcy.demo04;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/9 10:49
 */
public class ProdConsTraditionDemo2 {
    public static void main(String[] args) {
        ShareData2 shareData2 = new ShareData2();
        new Thread(() ->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Producer").start();

        new Thread(() ->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}

/**
 * 资源类
 */
class ShareData2 {
    private int number = 0;

    /**
     * 加
     */
    public synchronized void increment() throws InterruptedException {
        //1 判断
        while (number != 0) {
            // 等待，不能生产
            this.wait();
        }
        // 2 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3 通知唤醒
        this.notifyAll();
    }

    /**
     * 减
     */
    public synchronized void decrement() throws InterruptedException {
        //1 判断
        while (number == 0) {
            // 等待，不能生产
            this.wait();
        }
        // 2 干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3 通知唤醒
        this.notifyAll();
    }
}