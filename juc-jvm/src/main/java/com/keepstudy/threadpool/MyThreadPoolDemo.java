package com.keepstudy.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 线程获取方式4：线程池
 */
public class MyThreadPoolDemo {

    //自定义线程工厂
    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (null == name || name.isEmpty()) {
                name = "pool";
            }
            namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public static void main(String[] args) {
        //byoThreadPool();
        //手动创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                //Executors.defaultThreadFactory(),
                new NamedThreadFactory("mySelf"),
                //①默认超出最大线程池+队列容量，直接拒绝，抛异常
                //new ThreadPoolExecutor.AbortPolicy());
                //②超出自己能力范围的不处理，回退调用者，让调用者处理
                //new ThreadPoolExecutor.CallerRunsPolicy());
                //③超出范围，最早等待的不处理
                //new ThreadPoolExecutor.DiscardOldestPolicy());
                //④超出范围的直接不处理，丢数据
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 1; i <= 9; i++) {
                int temp = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务-"+temp);
                });
            }
        } finally {
            executorService.shutdown();
        }

    }

    /**
     * JDK自带线程池
     */
    private static void byoThreadPool() {
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
