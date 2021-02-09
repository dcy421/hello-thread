package com.dcy.demo02.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/14 8:07
 */
public class TestGaoPc3 {
    public static void main(String[] args) {
        Count3 count = new Count3();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.printA();
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                count.printC();
            }

        }, "C").start();
    }
}


class Count3 {

    private int num = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAA");
            num = 2;
            //唤醒指定的人
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printB() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBB");
            num = 3;
            //唤醒指定的人
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCC");
            num = 1;
            //唤醒指定的人
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}