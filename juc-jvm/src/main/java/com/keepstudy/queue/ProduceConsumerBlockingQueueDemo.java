package com.keepstudy.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 基于阻塞队列的生产--消费模型
 */

//资源类
class MyResource{
    //默认开启，进行生产消费
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue =null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产
     * @throws Exception
     */
    public void myProduce() throws Exception {
        String data= null;
        boolean returnValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            returnValue = blockingQueue.offer(data,2l, TimeUnit.SECONDS);
            if (returnValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列数据,data="+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列数据,data="+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止表示 flag="+flag);
    }

    /**
     * 消费
     * @throws Exception
     */
    public void myConsumer() throws Exception{
        String result = null;
        while (flag){
            result = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if ("".equalsIgnoreCase(result) || null == result){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过2s没有取到数据，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    /**
     * 停止生产和消费
     */
    public void stop(){
        flag = false;
    }
}
public class ProduceConsumerBlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(5));
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"生产线程启动成功");
                myResource.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"produce").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"消费线程启动成功");
                System.out.println();
                System.out.println();
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println();
        System.out.println();

        System.out.println("时间到，停止生产");
        myResource.stop();
    }
}
