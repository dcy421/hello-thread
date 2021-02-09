package com.dcy.demo02.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/13 13:50
 */
public class TestGaoPc2 {

    public static void main(String[] args) {
        Count2 count = new Count2();

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



class Count2 {

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /**
     * 加法
     */
    public void increment() {
        try {
            lock.lock();
            while (num != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减法
     */
    public void decrement() {
        try {
            lock.lock();
            while (num == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}