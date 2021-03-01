package com.phoenixhell.work;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile Boolean FLAG =true;
    private AtomicInteger atomicInteger =new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    void myProd() throws InterruptedException {
        String cakeCount=null;
        boolean offer;
        while(FLAG){
            cakeCount = atomicInteger.incrementAndGet()+"";
            System.out.println("******"+cakeCount+"*******");
            offer = blockingQueue.offer(cakeCount, 2L, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+cakeCount+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+cakeCount+"失败");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t 老板叫停 不生产了");
    }

    void myConsumer() throws InterruptedException {
        String cakeTake=null;
        while(FLAG){
            cakeTake = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==cakeTake || cakeTake.equalsIgnoreCase("")){
              FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有消费到数据 退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+cakeTake+"成功");
        }
    }

    void stop(){
        this.FLAG=false;
    }
}

public class ProdConsumer_BlockQueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"prod").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒时间到 main线程叫停");
        myResource.stop();
    }

}
