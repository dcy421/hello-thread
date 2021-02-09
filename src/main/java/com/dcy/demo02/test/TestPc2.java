package com.dcy.demo02.test;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/13 13:19
 */
public class TestPc2 {
    public static void main(String[] args) {
        Count count = new Count();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.decrement();
            }

        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.increment();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.decrement();
            }

        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.increment();
            }
        },"D").start();
    }
}


class Count {
    private int num = 0;


    /**
     * 加法
     */
    public synchronized void increment() {
        while (num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        this.notifyAll();
    }

    /**
     * 减法
     */
    public synchronized void decrement() {
        while (num == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        this.notifyAll();
    }
}