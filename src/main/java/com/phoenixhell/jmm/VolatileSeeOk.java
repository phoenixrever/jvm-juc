package com.phoenixhell.jmm;

import java.util.concurrent.TimeUnit;

class NumberDemo{
    private volatile Integer number=0;
    void addNumberTo60(){
        number=60;
    }

    public Integer getNumber() {
        return number;
    }
}
public class VolatileSeeOk {
    public static void main(String[] args) {
        NumberDemo numberDemo =new NumberDemo();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"分线程启动");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numberDemo.addNumberTo60();
            System.out.println(Thread.currentThread().getName()+"分线修改完程结束");
        },"aaa").start();
        while(numberDemo.getNumber()!=60){

        }
        System.out.println(Thread.currentThread().getName()+"--main----"+numberDemo.getNumber());
    }
}
