package com.phoenixhell.base;

import java.util.Arrays;

/**
 * @author phoenixhell
 * @since 2021/2/28 0028-上午 9:44
 * 手写冒泡排序
 */

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{43,32,76,-98,0,64,33,-21,32,99,1};
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
