package com.phoenixhell.container;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author phoenixhell
 * @since 2021/2/28 0028-上午 9:21
 */

public class ConCurrentMapSafe {
    public static void main(String[] args) {
        /**
         * Creates a new, empty map with the default initial table size (16).
         */
        ConcurrentHashMap<String, Object> ConcurrentHashMap = new ConcurrentHashMap<>();
        for(int i = 0; i < 3; i++) {
           new Thread(()->{
                ConcurrentHashMap.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
               System.out.println(ConcurrentHashMap);
           },String.valueOf(i)).start();
        }
    }
}
