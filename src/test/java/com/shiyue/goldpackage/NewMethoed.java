package com.shiyue.goldpackage;

import com.shiyue.springboot.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NewMethoed {
//
//
//    public void amount(){
//        List<User> userList = new ArrayList<>();

//        userList.add(new User(1L, "zhangsan", 23, new BigDecimal(55)));
//        userList.add(new User(2L, "lisi", 33, new BigDecimal(22)));
//        userList.add(new User(3L, "wangwu", 44, new BigDecimal(44)));
//        userList.add(new User(4L, "mazi", null, new BigDecimal(44)));

        // 对年龄进行统计
//        int ageCount = userList.stream()
//                .mapToInt(item -> item.getAge() == null ? 0 : item.getAge())
//                .sum();
//        System.out.println("年龄总和： " + ageCount);
//        //对资产进行统计
//        BigDecimal amounts1 = userList
//                .stream()
//                .filter()
//                .map(item -> item.getAmount() == null ? BigDecimal.ZERO : item.getAmount())
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println("资产总和1 :" + amounts1);
//
//        // 对资产进行统计 (这种方式会存在空指针异常，所以建议还是使用上面一种方式  )
//        BigDecimal amounts2 = userList
//                .stream()
//                .map(User::getAmount)
//                .reduce(BigDecimal::add)
//                .get();
//
//        System.out.println("资产总和2: " + amounts2);
//
//    }
}
