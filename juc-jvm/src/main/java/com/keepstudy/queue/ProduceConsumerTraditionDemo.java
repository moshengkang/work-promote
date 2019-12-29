package com.keepstudy.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 基于lock版本的生产-消费者模式
 */

//资源类
class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 增加
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断,尽量使用while判断，如果使用if判断需要对变量加volatile
            while (number != 0){
                //等待，不生产
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减少
     * @throws InterruptedException
     */
    public void less() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
public class ProduceConsumerTraditionDemo {
    public static void main(String[] args) {
        //一个初始值为0的变量 两个线程交替操作 一个加1 一个减1来5轮
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"produce").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.less();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer").start();

       /* new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"produce-2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.less();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer-2").start();*/
    }
}
