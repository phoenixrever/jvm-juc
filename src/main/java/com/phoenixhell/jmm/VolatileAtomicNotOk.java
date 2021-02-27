package com.phoenixhell.jmm;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicDemo{
    private AtomicInteger atomicInteger = new AtomicInteger();
    private volatile Integer number=0;

    void addNumberTo60(){
        number=60;
    }

    public Integer getNumber() {
        return number;
    }
    void addPlusPlus(){
        number++;
    }
    //参数不写默认为0
    void addAtomic(){
        atomicInteger.getAndIncrement();
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
public class VolatileAtomicNotOk {
    public static void main(String[] args) {
        AtomicDemo atomicDemo =new AtomicDemo();

        for(int i=0;i<20;i++){
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    atomicDemo.addAtomic();
                    atomicDemo.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        while(Thread.activeCount()>2){
            //使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，
            // 此线程有可能被执行，也有可能没有被执行。
            Thread.yield();
        }
        System.out.println(atomicDemo.getAtomicInteger());
        System.out.println(atomicDemo.getNumber());
    }
}
