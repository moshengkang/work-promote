package com.keepstudy;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2020/2/25 09:16
 * @Description: 类加载器
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        //System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());//null(BootStrap ClassLoader)

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());//null(BootStrap ClassLoader)
        System.out.println(myObject.getClass().getClassLoader().getParent());//ExtClassLoader
        System.out.println(myObject.getClass().getClassLoader());//AppClassLoader
    }
}
