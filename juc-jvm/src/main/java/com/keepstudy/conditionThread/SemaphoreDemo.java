package com.keepstudy.conditionThread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 信号量的主要用户两个目的,一个是用于多个共享资源的相互排斥使用,另一个用于并发资源数的控制.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //模拟3个停车位
        Semaphore semaphore = new Semaphore(3);
        //模拟6个汽车
        for(int i = 1;i<= 6;i++){
            new Thread(() -> {
                try {
                    //抢到资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t停3秒离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
