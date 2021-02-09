package com.dcy.demo02.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/13 9:55
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        // 函数式编程
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}

// lock锁
// 1、new ReentrantLock
// 2、加锁
// 3、解锁
class Ticket2 {
    // 票数
    int number = 50;

    // FairSync：公平锁，十分公平，可以先来后到
    // NonfairSync：非公平锁：可以插队（默认）
    Lock lock = new ReentrantLock();

    public void sale() {

        // 加锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩余：" + number);
            }
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}
