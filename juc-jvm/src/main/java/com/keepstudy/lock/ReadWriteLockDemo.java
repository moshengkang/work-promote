package com.keepstudy.lock;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 读写锁
 * 多个线程同时操作 一个资源类没有任何问题 所以为了满足并发量
 * 读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源来  就不应该有其他线程可以对资源进行读或写
 * 小总结:
 * 读读能共存
 * 读写不能共存
 * 写写不能共存
 * 写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许被分割 被打断
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义缓存
 */
class MyCache{
    /**
     * 保证可见性
     */
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 写入
     * @param key
     * @param value
     */
    public void put(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入"+key);
            //模拟网络延时
            try {TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t正在完成");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读取
     * @param key
     */
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取");
            //模拟网络延时
            try {TimeUnit.MICROSECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t正在完成"+value);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    /**
     * 清除
     */
    public void clear(){
        map.clear();
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for(int i = 1;i<= 5;i++){
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp+"",temp);
            },String.valueOf(i)).start();
        }

        for(int i = 1;i<= 5;i++){
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
