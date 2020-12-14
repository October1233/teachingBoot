package com.shiyue.springboot;

import com.shiyue.springboot.controller.Logincontroller;
import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.EazyUiMapper;
import com.shiyue.springboot.service.EazyUiservice;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;


public class Car {

    private static Logger logger = LoggerFactory.getLogger(Car.class);


    @Autowired
    EazyUiservice eazyUiservice;

    @Autowired
    EazyUiMapper eazyUiMapper;

    public String a = "12345";


    @Test
    public void acc() {
        List<User> users = eazyUiMapper.findAll();
        Optional<List<User>> opUser = Optional.ofNullable(users);
        System.out.println(opUser.toString());
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }


    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    @Test
    public void aaaa() {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

//        cars.forEach( Car::collide );
        cars.forEach(Car::repair);

    }

    public static void main(String args[]) {
        List names = new ArrayList();
        final String name2 = "cccc";
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");


//        names.forEach(name->System.out.printf(name+";"));
//        names.forEach(System.out::println);
        String ccc = null;
        if (ccc.equals("ccc")) {
            System.out.println("111");
        }
    }

    @Test
    public void testApple() {
        int add = 3;
        int allApple = 50;
        for (int apple=50;apple/add>0;apple=apple/add){
            allApple=allApple+apple/add;
        }
        System.out.println(allApple);
    }




}

