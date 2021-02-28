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
class MyCache{
    private  volatile Map<String,Object> map = new HashMap<>();
//    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock =new ReentrantReadWriteLock();

    void write(String key,Object value){
//        lock.lock();
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" key:"+key+" ------writing");
            TimeUnit.MILLISECONDS.sleep(500);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+" key:"+key +" -----write complete");
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
        try {
            System.out.println(Thread.currentThread().getName()+"------reading");
            TimeUnit.MILLISECONDS.sleep(500);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t +"+value+"-----reading complete");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            lock.unlock();
            readWriteLock.readLock().unlock();
        }
    }

}
public class WriteReadLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for(int i=0;i<5;i++){
            final int tempI=i;
            new Thread(()->{
                myCache.write(tempI+"", tempI+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempI=i;
            new Thread(()->{
                myCache.read(tempI+"");
            },String.valueOf(i)).start();
        }
    }
}
