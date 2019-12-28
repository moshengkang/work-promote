package com.keepstudy.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //addAndRemove();
        //putAndTake();
        offer();
    }

    /**
     * 超时
     * @throws InterruptedException
     */
    private static void offer() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        //超时2秒自动退出
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));
    }

    /**
     * 阻塞
     * @throws InterruptedException
     */
    private static void putAndTake() throws InterruptedException {
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println("****************");
        //将会阻塞
        //blockingQueue.take();
        //blockingQueue.put("a");
    }

    /**
     * 抛出异常
     */
    private static void addAndRemove() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
