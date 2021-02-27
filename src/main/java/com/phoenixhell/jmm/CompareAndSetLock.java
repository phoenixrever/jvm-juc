package com.phoenixhell.jmm;

import java.util.concurrent.atomic.AtomicReference;

public class CompareAndSetLock {
    private AtomicReference<Thread> atomicReference =new AtomicReference<>();
    private  Integer number=0;

    void  myLock(){
        Thread thread =Thread.currentThread();
        while(!atomicReference.compareAndSet(null, thread)){

        };
    }
    void myUnLock(){
        Thread thread =Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        CompareAndSetLock c=new CompareAndSetLock();
        for(int i=0;i<20;i++){
            new Thread(()->{
                c.myLock();
                for (int j = 0; j < 1000; j++) {
                    c.number++;
                }
                c.myUnLock();
            },String.valueOf(i)).start();
        }

        while(Thread.activeCount()>2){

        }
        System.out.println(c.number);
    }
}
