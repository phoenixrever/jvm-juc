//package com.phoenixhell.juc;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//public class StreamDemo {
//    private Integer id;
//    private String name;
//    private Integer age;
//
//    public static void main(String[] args) {
//        StreamDemo user1=new StreamDemo(11,"a",22);
//        StreamDemo user2=new StreamDemo(12,"b",23);
//        StreamDemo user3=new StreamDemo(13,"c",24);
//        StreamDemo user4=new StreamDemo(14,"d",25);
//        StreamDemo user5=new StreamDemo(16,"e",26);
//        List<StreamDemo> list = Arrays.asList(user1,user2,user3,user4,user5);
////        list.sort((o1,o2)-> -o1.name.compareTo(o2.name));
//        System.out.println(list.toArray()[0]);
//        System.out.println(list);
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).id%2==0&&list.get(i).age>24){
//                System.out.println(list.get(i).name.toUpperCase());
//                break;
//            }
//        }
//        Supplier<String> stringSupplier =()-> "123";
//        System.out.println(stringSupplier.get());
//
//        System.out.println("********************");
//        List<String> collect = list.stream().filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
//                //特别注意 一旦map了 返回的就是Stream<String>了 不在是类了
//                //map是会提取元素生成新类型stream
//                .map(u -> u.getName().toUpperCase())
////                .sorted((u1,u2)-> u2.compareTo(u1))
//                .sorted(Comparator.reverseOrder())
//                .limit(1L)
//                //Stream 转成list
//                .collect(Collectors.toList());
//        System.out.println(collect);
//    }
//}
