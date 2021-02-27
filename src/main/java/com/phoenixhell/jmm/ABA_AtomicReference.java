package com.phoenixhell.jmm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
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
