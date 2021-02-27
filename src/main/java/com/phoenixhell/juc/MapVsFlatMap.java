package com.phoenixhell.juc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapVsFlatMap {
    public static void main(String[] args) {
        String[] s1 = new String[]{"12", "34", "56", "78"};
        String[] s2 = new String[]{"ab", "cd", "ef", "gh"};
        List<String[]> list = Arrays.asList(s1, s2);
        //lamda 不加return就不能写大括号
        list.stream().map(x -> Arrays.stream(x).map(y -> y = y + "after")).forEach(x -> {
//            System.out.println(Arrays.toString(x.toArray()));
            System.out.println(Arrays.asList(x.toArray()));
        });
        System.out.println("***************************************");
        List<String> collect = list.stream().flatMap(x -> Arrays.stream(x).map(y -> y = "aaa"))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
