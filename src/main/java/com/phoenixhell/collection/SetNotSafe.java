package com.phoenixhell.collection;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author phoenixhell
 * @since 2021/2/28 0028-上午 8:55
 */

public class SetNotSafe {
    public static void main(String[] args) {
        /**
         * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
         * default initial capacity (16) and load factor (0.75).
         *         public HashSet() {
         *                 map = new HashMap<>();
         *         }
         *
         * private static final Object PRESENT = new Object();
         *         public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         */
        Set<String> set =new CopyOnWriteArraySet<>();
        for(int i = 0; i < 3; i++) {
           new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,6));
               System.out.println(set);
           },String.valueOf(i)).start();
        }
    }
}
