package com.phoenixhell.InterfaceMultiImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantSingleton {
    private volatile static ReentrantSingleton instance=null;
    private static Lock lock =new ReentrantLock();
    private ReentrantSingleton(){
        System.out.println("constructor");
    }

    public static ReentrantSingleton getInstance(){
        if(instance==null){
            try {
                lock.lock();
                if(instance==null){
                    instance=new ReentrantSingleton();
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
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                getInstance();
            },String.valueOf(i)).start();
        }
    }
}
