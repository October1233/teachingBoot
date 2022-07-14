package com.shiyue.proxy;

import com.shiyue.proxy.service.impl.TaoBao;
import com.shiyue.springboot.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ShopMain {

    public static void main(String[] args) {
        //创建代理的商家taobao对象
        TaoBao taoBao = new TaoBao();
        //通过代理类，实现购买u盘，增加了优惠券，红包等等
        float price = taoBao.sell(1);
        ConcurrentHashMap<Object, Object> oncurrentHashMap = new ConcurrentHashMap<>();
        oncurrentHashMap.put("null",null);
        System.out.println("通过淘宝的商家，购买u盘单价：" + price);
        List<User> list = new ArrayList();
        Set<User> playerSet = new TreeSet<>(Comparator.comparing(User::getAge));
        playerSet.addAll(list);
    }}
