package com.keepstudy.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: CAS产生的ABA问题(在过程中有问题)以及解决办法
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    //解决ABA问题;初始值，版本号
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("以下是ABA问题的产生");
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(100, 101)+"\t"+atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101, 100)+"\t"+atomicReference.get());
        },"t1").start();


        new Thread(() -> {
            //睡眠1s，产生ABA问题
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"t2").start();

        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("以下是ABA问题的解决");

        new Thread(() -> {
            //获取第一次版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2次版本号："+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3次版本号："+atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(() -> {
            //获取第一次版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
            try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
            boolean result =atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改是否成功："+result+"\t当前最新值版本号："+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前最新值：" + atomicStampedReference.getReference());
        },"t4").start();
    }
}
