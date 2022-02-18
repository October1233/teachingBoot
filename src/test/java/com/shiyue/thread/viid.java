package com.shiyue.thread;

import com.shiyue.springboot.domain.ScreenConditions;
import com.shiyue.springboot.domain.User;

import java.lang.reflect.Field;

public class viid {
    
    public static void main(String[] args) {
        ScreenConditions screenConditions = new ScreenConditions();
        screenConditions.setConditionsName("2");
        User user = new User();
        user.setScreenConditions(screenConditions);
        Field field = null;
        Field field1 = null;
        Class<?> clazz = user.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField("screenConditions");
                field1 = field.getClass().getDeclaredField("conditionsName");
            } catch (Exception e) {


            }
        }
        System.out.println(field);
        System.out.println(field1);
    }

//    public static void main(String[] args) {
//        int a = 0;
//        for (int i = 0;i<5;i++){
//            int b = 1;
//            a = a+b;
//        }
//        System.out.println(a);
//    }
}
