package com.phoenixhell.jmm;

import java.util.concurrent.atomic.AtomicReference;

public class CompareAndSetSingleton {
    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private static CompareAndSetSingleton instance = null;

    private CompareAndSetSingleton() {
        System.out.println(Thread.currentThread().getName() + "----constructor");
    }

    static void myLock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    static void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static CompareAndSetSingleton getInstance() {
        if (instance == null) {
            myLock();
            if (instance == null) {
                instance = new CompareAndSetSingleton();
            }
            myUnLock();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(CompareAndSetSingleton::getInstance, String.valueOf(i)).start();
        }
    }
}
