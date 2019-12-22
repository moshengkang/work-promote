package com.keepstudy.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 原子引用
 */
@Data
@AllArgsConstructor
class User{
    private String name;
    private Integer age;
}

public class AtomicRefenceDemo {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 25);
        User lisi = new User("lisi", 26);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zhangsan);

//        new Thread(() ->{
//            System.out.println(atomicReference.compareAndSet(zhangsan, lisi)+Thread.currentThread().getName()+"\t"+atomicReference.get());
//        },"t1").start();
//
//        new Thread(() ->{
//            System.out.println(atomicReference.compareAndSet(zhangsan, lisi)+Thread.currentThread().getName()+"\t"+atomicReference.get());
//        },"t2").start();

        System.out.println(atomicReference.compareAndSet(zhangsan, lisi)+"\t"+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(zhangsan, lisi)+"\t"+atomicReference.get());
    }
}

