package com.keppstudy;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: lambda表达式（函数式编程）
 */
@FunctionalInterface
interface Foo {
    //public void sayHello();
    int add(int x, int y);
    default int mul(int x,int y){
        return x*y;
    }
    static int div(int x,int y){
        return x/y;
    }
}
public class LambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("非Lambda");
//            }
//        };
//        foo.sayHello();
//
//        Foo foo1 = () -> {
//            System.out.println("Lambda");
//        };
//        foo1.sayHello();
        Foo foo = (x,y) -> {
            return x * y;
        };
        System.out.println(foo.add(2, 5));
        System.out.println(foo.mul(2, 5));
        System.out.println(Foo.div(10,5));
    }
}
