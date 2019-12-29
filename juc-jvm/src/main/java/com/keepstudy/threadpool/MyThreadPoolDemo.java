package com.keepstudy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 线程获取方式4：线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //单一线程池
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //缓存线程池
        //ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
//                executorService.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(Thread.currentThread().getName()+"\t办理业务");
//                    }
//                });
            }
        } finally {
            //一定要关闭线程池
            executorService.shutdown();
        }
    }
}
