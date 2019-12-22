package com.keepstudy.juc;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: DCL单例：双重检锁检查
 */
public class SingletonDemo {

    //加上volatile禁止指令重排
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 构造方法");
    }

    public static SingletonDemo getInstance(){
        if (null == instance){
            synchronized (SingletonDemo.class){
                if (null == instance){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
