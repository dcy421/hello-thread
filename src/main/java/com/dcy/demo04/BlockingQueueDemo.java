package com.dcy.demo04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2021/2/9 9:18
 * <p>
 * 1 队列
 * 2 阻塞对象
 * 2.1 阻塞队列有没有好的一面
 * 2.2 不得部阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//        addAndRemove(blockingQueue);
//        offerAndPoll(blockingQueue);
//        putAndTake(blockingQueue);
//        outOfTime(blockingQueue);
    }

    private static void outOfTime(BlockingQueue<String> blockingQueue) throws InterruptedException {
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        // 超时等候，2秒钟添加不了，返回false
//        System.out.println(blockingQueue.offer("x", 2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        // 超时等候，2秒钟没取到，返回null
//        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }

    /**
     * 阻塞
     *
     * @param blockingQueue
     * @throws InterruptedException
     */
    private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 列队已满，在添加阻塞
//        blockingQueue.put("xxx");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 列队已满，在取阻塞
        System.out.println(blockingQueue.take());
    }

    /**
     * 特殊组，不报错
     *
     * @param blockingQueue
     */
    private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 在添加 返回false
//        System.out.println(blockingQueue.offer("xx"));

        // 取队首
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 在取就返回null
        System.out.println(blockingQueue.poll());
    }


    /**
     * 抛出异常组
     *
     * @param blockingQueue
     */
    private static void addAndRemove(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 满了部可以加入 报异常 java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("xx"));
        // 取队首
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 满了在取 报异常 java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }
}
