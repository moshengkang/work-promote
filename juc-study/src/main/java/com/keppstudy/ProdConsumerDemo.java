package com.keppstudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 线程通信
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 * 1   高聚低合前提下，线程操作资源类
 * 2   判断/干活/通知
 * 3   防止虚假唤醒
 * 知识小总结 = 多线程编程套路+while判断+新版写法
 */

class Aricondition {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0){
                condition.await();//this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (0 == number) {
                condition.await();//this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

//传统版
//    public synchronized void increment() throws Exception{
//        //使用if会出现虚假唤醒
//        while (number != 0){
//            this.wait();
//        }
//        number++;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        this.notifyAll();
//    }

//    public synchronized void decrement() throws Exception{
//        while (0 == number) {
//            this.wait();
//        }
//        number--;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        this.notifyAll();
//    }
}

public class ProdConsumerDemo {
    public static void main(String[] args) {
        Aricondition aricondition = new Aricondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    aricondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i <10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                    aricondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                    aricondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i <10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    aricondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
