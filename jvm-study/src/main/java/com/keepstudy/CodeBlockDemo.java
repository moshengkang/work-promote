package com.keepstudy;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: JVM规定（执行顺序）：静态代码块 > 代码块 > 构造方法
 */
class Code {
    public Code() {
        System.out.println("Code的构造方法1111");
    }

    {
        System.out.println("Code的构造块2222");
    }

    static {
        System.out.println("Code的静态代码块3333");
    }
}
public class CodeBlockDemo {
    {
        System.out.println("CodeBlockDemo的构造块444");
    }

    static {
        System.out.println("CodeBlockDemo的静态代码块555");
    }

    public CodeBlockDemo() {
        System.out.println("CodeBlockDemo的构造方法666");
    }

    public static void main(String[] args) {
        System.out.println("分割线========================");
        new Code();
        System.out.println("------------------------------");
        new Code();
        System.out.println("------------------------------");
        new CodeBlockDemo();
    }
}
