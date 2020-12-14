package com.shiyue.superUtil;

import java.util.Arrays;
import java.util.Optional;

public class SuperUtil {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void BubbleSort(int[] arr) {
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = new int[]{1,6,2,2,5};
        BubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        String s="sfdasf a $ fasf";
        String result1 = Optional.ofNullable(s)
                .map(str -> str.replaceAll("$", ""))
                .orElse(s);
        System.out.println(result1);
    }



}
