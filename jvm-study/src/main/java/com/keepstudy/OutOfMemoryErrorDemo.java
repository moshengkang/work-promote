package com.keepstudy;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: java堆溢出（OOM）
 */
public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[60*1024*1024];
    }
}
