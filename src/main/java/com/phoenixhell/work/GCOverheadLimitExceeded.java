package com.phoenixhell.work;

import java.util.ArrayList;
import java.util.List;

public class GCOverheadLimitExceeded {
    public static void main(String[] args) {
        int i = 0;
        List<String> list =new ArrayList<>();

        try {
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("***"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
