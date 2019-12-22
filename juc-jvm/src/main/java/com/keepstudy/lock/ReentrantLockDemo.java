package com.keepstudy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 可重入锁
 */
class Phone implements Runnable {
    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName()+"\t invoke method1");
        method2();
    }
    public synchronized void method2(){
        System.out.println(Thread.currentThread().getName()+"\t invoke method2");
    }

    @Override
    public void run() {
        get();
    }
    Lock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        } finally {
            lock.unlock();
        }
    }
    
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.method1();
        },"t1").start();

        new Thread(() -> {
            phone.method1();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        new Thread(phone,"t3").start();
        new Thread(phone,"t4").start();
    }
}
