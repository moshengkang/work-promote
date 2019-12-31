package com.keepstudy.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2019/12/31 16:44
 * @Description: 死锁
 * 死锁是指两个或者以上的进程在执行过程中,
 * 因争夺资源而造成的一种相互等待的现象,
 * 若无外力干涉那他们都将无法推进下去
 */
class HoldThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有:"+lockA+"尝试获得:"+lockB);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有:"+lockB+"尝试获得:"+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA,lockB),"threadA").start();
        new Thread(new HoldThread(lockB,lockA),"threadB").start();
    }
}
