package com.keepstudy;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2020/2/26 09:06
 * @Description: 线程只可以start一次,否则：Exception in thread "main" java.lang.IllegalThreadStateException
 */
public class MyThread {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
