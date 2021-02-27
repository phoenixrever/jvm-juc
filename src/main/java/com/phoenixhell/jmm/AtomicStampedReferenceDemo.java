package com.phoenixhell.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference =
                new AtomicStampedReference<>(100, 1);
        new Thread(()->{
        //等待1秒 t2 拿到一样的版本号
            int stamp=stampedReference.getStamp();
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b1 = stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("修改结果："+b1+"\t"+"当前值"+stampedReference.getReference()+"当前版本"+stampedReference.getStamp());
            boolean b2 = stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println("修改结果："+b2+"\t"+"当前值"+stampedReference.getReference()+"当前版本"+stampedReference.getStamp());
        },"t1").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            //等待3秒待t1运行完成修改版本号
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = stampedReference.compareAndSet(100, 2021, stamp, stamp + 1);
            System.out.println("修改结果："+b+"\t"+"当前值"+stampedReference.getReference()+"当前版本"+stampedReference.getStamp());
        },"t2").start();
    }
}
