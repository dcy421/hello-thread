package com.dcy.demo04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/8 13:47
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        System.out.println("=============synchronized===============");
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=============ReentrantLock===============");

        new Thread(() -> {
            try {
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3").start();

        new Thread(() -> {
            try {
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t4").start();
    }
}

class Phone implements Runnable {
    // Synchronized  Test
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    @Override
    public void run() {
        get();
    }

    // ReentrantLock Test
    Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "get()");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "set()");
        } finally {
            lock.unlock();
        }
    }
}
