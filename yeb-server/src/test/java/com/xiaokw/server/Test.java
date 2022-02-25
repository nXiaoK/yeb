package com.xiaokw.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    @org.junit.Test
    public  void test(){
        class User{
            String name;
            String age;

            public User(String name, String age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }
        }

        List<User> users = new ArrayList<>();
        users.add(new User("zhangsan", "99"));
        users.add(new User("lisi", "88"));
        String[] strings = users.stream().map(User::getName).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }

    @org.junit.Test
    public void test2(){
        System.out.println(3*0.1);
        System.out.println(3*0.1 == 0.3);
    }
}
