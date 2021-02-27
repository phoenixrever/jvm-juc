package com.phoenixhell.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(7,()->{
            System.out.println("頑張て、諦めないて、お前は絶対できる");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"---"+"戦う");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"---"+"---");
            },String.valueOf(i)).start();
        }
    }
}
