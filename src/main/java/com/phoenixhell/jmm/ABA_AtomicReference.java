package com.phoenixhell.jmm;

import java.util.concurrent.atomic.AtomicReference;


class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ABA_AtomicReference {
    public static void main(String[] args) {
        User u1 = new User("shadow", 32);
        User u2 = new User("phoenixhell", 33);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(u1);
        System.out.println(userAtomicReference.compareAndSet(u1, u2)+"---"+userAtomicReference.get());

    }

}
