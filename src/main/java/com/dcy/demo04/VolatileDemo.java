package com.dcy.demo04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：dcy
 * @Description: JMM验证
 * 1、验证volatile的可见性
 * 1.1 假如int number = 0;number变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2 添加了volatile，可以解决可见性问题。
 * 2 验证volatile不保证原子性。
 * 2.1 原子性指的是什么意思。不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以加塞或者被分割。
 * 需要整体完整 要么同时成功，要么同时失败。
 * 2.2 volatile 不保证原子性
 * 2.3 why
 * 2.4 如何解决原子性
 * 加 sync
 * 用 AtomicInteger
 * @Date: 2021/2/7 14:54
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();

        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上门20个线程都计算完成后，再用main线程取得最终结果值
        // Thread.activeCount() 表示去启动的线程个数
        // Thread.yield() 表示礼让线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.atomicInteger.get());

    }

    /**
     * volatile 可以保证可见性，及时通知其他线程，主屋里内存的值以及被修改。
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                // 睡3秒钟
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "AAA").start();

        // 第二个线程
        while (myData.number == 0) {
            // main线程就一直等待，知道number值不在等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value:" + myData.number);
    }
}

class MyData {
    // volatile关键字
    volatile int number = 0;
    /**
     * JUC下的AtomicInteger
     */
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addT060() {
        this.number = 60;
    }

    /**
     * 请注意：此时number前面是加了volatile关键字修饰的，volatile不保证原子性。
     */
    public void addPlus() {
        this.number++;
    }

    /**
     * 原子性+1
     */
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}