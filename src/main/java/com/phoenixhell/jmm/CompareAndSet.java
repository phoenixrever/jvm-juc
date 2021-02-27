package com.phoenixhell.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSet {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(atomicInteger.compareAndSet(5, 2021) + "\t" + Thread.currentThread().getName() + "--" + atomicInteger.get());
            }, String.valueOf(i)).start();
        }
    }
}
