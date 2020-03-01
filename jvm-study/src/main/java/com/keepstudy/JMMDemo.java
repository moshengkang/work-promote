package com.keepstudy;

import java.util.concurrent.TimeUnit;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: JMM(java内存模型--可见性)
 */
class MyNumber {
    volatile int number = 10;

    public void add() {
        number = 1025;
    }
}
public class JMMDemo {
    public static void main(String[] args) {
        MyNumber myNumber =  new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            myNumber.add();
            System.out.println(Thread.currentThread().getName()+"\t update number value;"+myNumber.number);
        },"AAA").start();

        while (10 == myNumber.number) {

        }
        System.out.println(Thread.currentThread().getName()+"\t work over");
    }
}
