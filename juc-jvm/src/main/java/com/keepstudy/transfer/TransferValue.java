package com.keepstudy.transfer;

/**
 * @Author: moshengkang
 * @e-mial: 1634414600@qq.com
 * @Version: 1.0
 * @Description: 作用域
 */
class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }
}
public class TransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("xxx");
    }

    public void changeValue3(String string) {
        string = "xxx";
    }

    public static void main(String[] args) {
        TransferValue transferValue = new TransferValue();
        int age = 20;
        transferValue.changeValue1(age);
        System.out.println("age----"+age);

        Person person = new Person("abc");
        transferValue.changeValue2(person);
        System.out.println("name----"+person.getName());

        String string = "abc";
        transferValue.changeValue3(string);
        System.out.println("String----"+string);
    }
}
