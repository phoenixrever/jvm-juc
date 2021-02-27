package com.phoenixhell.InterfaceMultiImpl;

public class Man implements Person {
    @Override
    public void eat() {
        System.out.println("man eat");
    }

    @Override
    public void sleep() {
        System.out.println("man sleep");
    }
}
