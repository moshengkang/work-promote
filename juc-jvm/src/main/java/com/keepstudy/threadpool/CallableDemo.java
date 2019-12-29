package com.keepstudy.threadpool;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: thread方式三，实现Callable接口
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 方式一：继承Thread类
 */
class MyThread1 extends Thread{

}

/**
 * 方式二:实现Runnable接口
 */
class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable come in");
    }
}

/**
 * 方式三:实现Callable接口
 */
class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable come in");
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        return 1024;
    }
}

public class CallableDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyThread1 myThread1 = new MyThread1();
//        myThread1.start();
        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();

        MyThread3 myThread3 = new MyThread3();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread3);
        new Thread(futureTask).start();
//        Integer integer = futureTask.get();
//        System.out.println(integer);
        int a =100;
        int b =0;
        while (!futureTask.isDone()){
            b = futureTask.get();
        }
        System.out.println("*****result="+(a+b));
    }
}
