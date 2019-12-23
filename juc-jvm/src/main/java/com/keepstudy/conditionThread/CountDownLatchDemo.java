package com.keepstudy.conditionThread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2019/12/23 18:40
 * @Description: CountDownLatchDemo
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
