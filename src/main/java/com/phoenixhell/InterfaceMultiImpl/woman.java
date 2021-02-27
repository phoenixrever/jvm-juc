package com.phoenixhell.InterfaceMultiImpl;

public class woman implements Person {
    @Override
    public void eat() {
        System.out.println("woman eat");
    }

    @Override
    public void sleep() {
        System.out.println("woman sleep");
    }
}
