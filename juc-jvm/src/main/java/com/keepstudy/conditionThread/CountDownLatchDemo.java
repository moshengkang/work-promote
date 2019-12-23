package com.keepstudy.conditionThread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2019/12/23 18:40
 * @Description: CountDownLatchDemo
 * CountDownLatch主要有两个方法,当一个或多个线程调用await方法时,调用线程会被阻塞.
 * 其他线程调用countDown方法计数器减1(调用countDown方法时线程不会阻塞),
 * 当计数器的值变为0,因调用await方法被阻塞的线程会被唤醒,继续执行.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1;i <= 6;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+"上完自习");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"班长关门");
    }
}
