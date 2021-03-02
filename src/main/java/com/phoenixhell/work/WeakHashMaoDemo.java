package com.phoenixhell.work;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author phoenixhell
 * @since 2021/3/2 0002-下午 4:54
 */

public class WeakHashMaoDemo {
    public static void main(String[] args) {
        normalMap();
        System.out.println("*****WeakHashMap*****");
        WeakHashMap<Object, String> weakHashMap = new WeakHashMap<>();
        Object key2=new Object();
        String value =new String("WeakHashMap");
        weakHashMap.put(key2, value);
        System.out.println(weakHashMap);
        key2=null;
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap);
    }

    private static void normalMap() {
        HashMap<Object, String> hashMap = new HashMap<>();
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Object key2=new Object();
        String value =new String("HashMap");
        hashMap.put(key2, value);

        key2=null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap);
    }
}
