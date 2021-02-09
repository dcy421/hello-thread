package com.dcy.demo04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/9 10:53
 */
public class ProdConsBlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            myResource.myProducer();
        }, "Producer").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            myResource.myConsumer();
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒钟后，叫停");
        myResource.stop();
    }
}

class MyResource {
    // 默认开启，进行生产和消费。保证多线程可见性
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者
     */
    public void myProducer() {
        String data;
        boolean retValue;
        while (flag) {
            try {
                data = atomicInteger.incrementAndGet() + "";
                retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (retValue) {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\tFLAG==false，停止生产");
    }

    /**
     * 消费者
     */
    public void myConsumer() {
        String result;
        while (flag) {
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (result == null || "".equalsIgnoreCase(result)) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有取到，消费退出");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止
     */
    public void stop() {
        this.flag = false;
    }
}