package com.phoenixhell.work;

public class ClassRunOrderTest {
    {
        System.out.println("test");
    }

    public ClassRunOrderTest(){
        System.out.println("Test constructor");
    }
    static {
        System.out.println("---static---");
    }
    public static void main(String[] args) {
        System.out.println("----------main------------");
        ClassRunOrderTest test = new ClassRunOrderTest();
    }
}
