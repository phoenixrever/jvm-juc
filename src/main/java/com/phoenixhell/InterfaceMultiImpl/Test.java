package com.phoenixhell.InterfaceMultiImpl;

public class Test {
    {
        System.out.println("test");
    }

    public Test(){
        System.out.println("Test constructor");
    }
    static {
        System.out.println("---static---");
    }
    public static void main(String[] args) {
        System.out.println("----------main------------");
        Test test = new Test();
    }
}
