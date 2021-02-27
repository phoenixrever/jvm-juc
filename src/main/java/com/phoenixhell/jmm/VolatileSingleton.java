package com.phoenixhell.jmm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class VolatileSingleton {
    private static volatile VolatileSingleton instance=null;
    private static Lock lock =new ReentrantLock();
    private VolatileSingleton(){
        System.out.println(Thread.currentThread().getName()+"----active");
    }

    public static VolatileSingleton getInstance() {
        //DCL  double check lock 双端检锁机制
        if(instance==null){
//            synchronized (VolatileSingleton.class){
//                if(instance ==null){
//                    instance=new VolatileSingleton();
//                }
//            }
            lock.lock();
            try {
                if(instance ==null){
                    instance=new VolatileSingleton();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                VolatileSingleton instance = VolatileSingleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
