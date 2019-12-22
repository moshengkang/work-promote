package com.keepstudy.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 集合不安全问题
 */
public class ContainerNoSafeDemo {
    public static void main(String[] args) {
        //notSafe();
        //vectorTest();
        //collectionsTest();
        copyOnWriteArrayListTest();
    }

    /**
     * 解决办法3：使用juc包下面的CopyOnWriteArrayList类
     * 写时复制 copyOnWrite 容器,即写时复制的容器,
     * 往容器添加元素的时候,不直接往当前容器 object[] 添加,
     * 而是先将当前容器 object[] 进行 copy 复制出一个新的 object[] newElements,
     * 然后向新容器object[] newElements 里面添加元素，添加元素后,
     * 再将原容器的引用指向新的容器 setArray(newElements);
     * 这样的好处是可以对copyOnWrite容器进行并发的读,而不需要加锁
     * 因为当前容器不会添加任何容器.所以copyOnwrite容器也是一种
     * 读写分离的思想,读和写不同的容器.
     */
    private static void copyOnWriteArrayListTest() {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 1;i<= 30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * ArrayList线程不安全
     * 故障现象：java.util.ConcurrentModificationException
     */
    private static void notSafe() {
        List<String> list = new ArrayList<>();
        for(int i = 1;i<= 30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * 解决方法1：使用Vector
     */
    private static void vectorTest() {
        List<String> list = new Vector<>();
        for(int i = 1;i<= 30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    /**
     * 解决办法2：使用Collections辅助类
     */
    private static void collectionsTest() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for(int i = 1;i<= 30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
