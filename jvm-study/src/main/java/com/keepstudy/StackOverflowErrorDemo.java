package com.keepstudy;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: java栈溢出
 */
public class StackOverflowErrorDemo {
    private void say(){
        say();
    }

    public static void main(String[] args) {
        StackOverflowErrorDemo demo = new StackOverflowErrorDemo();
        //Exception in thread "main" java.lang.StackOverflowError
        demo.say();
    }
}

