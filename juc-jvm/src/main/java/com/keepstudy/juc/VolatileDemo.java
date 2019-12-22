package com.keepstudy.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: volatile:可见性，不保证原子性，禁止指令重排
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        atomicByVolatile(myData);

        //visibilityByVolatile(myData);
    }

    /**
     * 用atomic保证原子性
     * @param myData
     */
    private static void atomicByVolatile(MyData myData) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addSelf();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (2 < Thread.activeCount()){
            //礼让线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number="+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t finally atomicInteger="+myData.atomicInteger);
    }

    /**
     * 验证volatile可见性
     * @param myData
     */
    private static void visibilityByVolatile(MyData myData) {
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t,come in number="+myData.number);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName()+"\t,update number="+myData.number);
        },"AAA").start();

        while (0 == myData.number){

        }
        System.out.println(Thread.currentThread().getName()+"\t,number="+myData.number);
    }
}
class MyData{
    volatile int number = 0;

    public void add(){
        this.number = 60;
    }

    public void addSelf(){
        this.number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}