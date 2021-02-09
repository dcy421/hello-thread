package com.dcy.demo04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/9 10:33
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 * 1 线程    操作   资源类
 * 2 判断    干活   通知
 * 3 防止虚假唤醒机制
 */
public class ProdConsTraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() ->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Producer").start();

        new Thread(() ->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
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
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 加
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减
     */
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number == 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}