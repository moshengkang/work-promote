package com.keepstudy.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: cas:compareAndSet(比较并交换)
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t current value "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2018)+"\t current value "+atomicInteger.get());

    }
}
