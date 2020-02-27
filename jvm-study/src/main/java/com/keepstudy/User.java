package com.keepstudy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 莫升康
 * @e-mail: 1634414600@qq.com
 * @Date: 2020/2/27 14:43
 * @Description: 对list中数据进行分组
 */
public class User {
    private String name;
    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static void test() {
        List<User> list = new ArrayList<>();
        //User 实体 测试用 String id,name;
        //当前测试以id来分组，具体请按开发场景修改
        list.add(new User("1", "1"));
        list.add(new User("1", "2"));
        list.add(new User("2", "2"));
        list.add(new User("2", "3"));
        list.add(new User("2", "4"));
        list.add(new User("3", "3"));

        //初始化一个map
        Map<String, List<User>> map = new HashMap<>();
        for(User user : list) {
            String key = user.getName();
            if(map.containsKey(key)) {
                //map中存在以此id作为的key，将数据存放当前key的map中
                map.get(key).add(user);
            } else {
                //map中不存在以此id作为的key，新建key用来存放数据
                List<User> userList = new ArrayList<>();
                userList.add(user);
                map.put(key, userList);
            }
        }
        //分组结束，map中的数据就是分组后的数据
        System.out.println(map);
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        System.out.println(map.get("3"));
    }

    public static void main(String[] args) {
        test();
    }
}
