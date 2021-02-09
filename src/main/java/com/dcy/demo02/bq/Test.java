package com.dcy.demo02.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/1/15 13:42
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
//        test2();
//        test3();
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        // 队列的大小
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.element());// 查看队首元素是谁
        // java.lang.IllegalStateException: Queue full 抛出异常
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，不抛出异常
     */
    public static void test2() {
        // 队列的大小
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d")); // 返回false 不抛出异常

        System.out.println(blockingQueue.peek());// 查看队首元素是谁

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());// 取不到值就是null

    }

    /**
     * 等待，阻塞（一直等待）
     */
    public static void test3() throws InterruptedException {
        // 队列的大小
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");// 队列没有位置了，一直阻塞

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());// 没有元素，一直阻塞

    }


    /**
     * 等待，阻塞（超时）
     */
    public static void test4() throws InterruptedException {
        // 队列的大小
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS)); // 如果2秒没填进去，就退出返回false

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));// 取不到值就是null，就退出

    }
}
