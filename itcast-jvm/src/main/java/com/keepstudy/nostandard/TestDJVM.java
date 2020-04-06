package com.keepstudy.nostandard;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 测试-D非标准参数
 */
public class TestDJVM {
    public static void main(String[] args) {
        String str = System.getProperty("str");
        if (null == str) {
            System.out.println("小康");
        } else {
            System.out.println(str);
        }
    }
}
