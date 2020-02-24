package com.keppstudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2020/2/24 10:00
 * @Description: 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.print(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.print(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.print(15);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}

/**
 * 资源类
 */
class ShareData {
    //通知标识:A=1,B=2,C=3
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

//    public void print(int frequency) throws Exception{
//        if (5 == frequency){
//            print5();
//        } else if (10 == frequency) {
//            print10();
//        } else {
//            print15();
//        }
//    }

    public void print(int frequency) throws Exception{
        lock.lock();
        try {
            if (5 == frequency){
                while (1 != number) {
                    condition1.await();
                }
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+number);
                }
                number = 2;
                condition2.signal();
            } else if (10 == frequency) {
                while (2 != number) {
                    condition2.await();
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+number);
                }
                number = 3;
                condition3.signal();
            } else {
                while (3 != number) {
                    condition3.await();
                }
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+number);
                }
                number = 1;
                condition1.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

//    public void print5() throws Exception {
//        lock.lock();
//        try {
//            while (1 != number) {
//                condition1.await();
//            }
//            for (int i = 1; i <= 5; i++) {
//                System.out.println(Thread.currentThread().getName()+"\t"+number);
//            }
//            number = 2;
//            condition2.signal();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void print10() throws Exception {
//        lock.lock();
//        try {
//            while (2 != number) {
//                condition2.await();
//            }
//            for (int i = 1; i <= 10; i++) {
//                System.out.println(Thread.currentThread().getName()+"\t"+number);
//            }
//            number = 3;
//            condition3.signal();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void print15() throws Exception {
//        lock.lock();
//        try {
//            while (3 != number) {
//                condition3.await();
//            }
//            for (int i = 1; i <= 15; i++) {
//                System.out.println(Thread.currentThread().getName()+"\t"+number);
//            }
//            number = 1;
//            condition1.signal();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
}