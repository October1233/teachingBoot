package com.shiyue.studybass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Slf4j
public class StreamAPI {

    public static void main(String[] args) {

        List<String> alpha = Arrays.asList("a", "b", "c", "d");

        //Before Java8 普通的增强型for循环
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println(alpha); //[a, b, c, d]
        System.out.println(alphaUpper); //[A, B, C, D]

        // Java 8 用流魂环操作
        List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect); //[A, B, C, D]

        // Extra/此外, streams apply to any data type.流可以应用到任何数据类型
        List<Integer> num = Arrays.asList(1,2,3,4,5);
        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(collect1); //[2, 4, 6, 8, 10]

        // 1. Array
        String[] idArray = {"1","1","4","6","3"};
        List<String> list2 = Arrays.stream(idArray).collect(Collectors.toList());
        list2.stream().forEach(System.out::println);
        System.out.println(list2.toString());
    }

    @Test
    public void dealOrder() throws InterruptedException {
        double sum = 0;
        for (int i = 0; i < 20; i++) {
            TimeUnit.MILLISECONDS.sleep(100);// 模拟处理单个订单消耗00毫秒，20个订单为2秒
            sum += i * 50.00;
        }
        System.out.println(sum);
    }


}
