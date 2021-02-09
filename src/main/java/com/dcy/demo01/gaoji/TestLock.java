package com.dcy.demo01.gaoji;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：dcy
 * @Description: 测试lock锁
 * @Date: 2021/1/12 16:24
 */
public class TestLock {

    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }

}

class TestLock2 implements Runnable {

    private int ticketNums = 10;
    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                // 加锁
                lock.lock();
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                } else {
                    break;
                }
            } finally {
                // 解锁
                lock.unlock();
            }

        }
    }
}
