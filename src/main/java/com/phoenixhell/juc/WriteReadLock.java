package com.phoenixhell.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//lock 2个写直接没有固定顺序
//readwritelock  读写直接有顺序 写完成后才是读
class ReadwritelockDemo{
    private  Map<String,Object> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock =new ReentrantReadWriteLock();

    void write(String s,Object o){
//        lock.lock();
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+" key:"+s+" ------writing");
        try {

//            TimeUnit.MILLISECONDS.sleep(500);
            map.put(s, o);
            System.out.println(Thread.currentThread().getName()+" key:"+s +" -----write complete");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            lock.unlock();
            readWriteLock.writeLock().unlock();
        }
    }
    void read(String key){
//        lock.lock();
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"------reading");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"-----reading complete");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            lock.unlock();
            readWriteLock.readLock().unlock();
        }
    }

}
public class WriteReadLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReadwritelockDemo d =new ReadwritelockDemo();
        for(int i=0;i<5;i++){
            final int tempI=i;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(()->{
                d.write(tempI+"", tempI+"");
            },String.valueOf(i)).start();
//
//            new Thread(()->{
//                d.read(tempI+"");
//            },String.valueOf(i)).start();
        }
    }
}
