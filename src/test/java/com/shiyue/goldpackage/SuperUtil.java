package com.shiyue.goldpackage;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;

public class SuperUtil extends GoldMethod{


    @Test
    public void supplier(){
        List<Map> maps = new ArrayList<>();
        for (int i=0;i<10000;i++){
            Map map = mapSupplier.get();
            maps.add(map);
        }
    }
    @Test
    public void simple(){
        List<Map> maps = new ArrayList<>();
        for (int i=0;i<10000;i++){
            Map map = new HashMap();
            maps.add(map);
        }
    }


    @Test
    public void sadasd(){
        String serialNum = 0111 + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        System.out.println(serialNum);
        String serialNum1 = 0111 + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        System.out.println(serialNum1);
        String serialNum11 = 0111 + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        System.out.println(serialNum11);

    }


    @Test
    public void sadassd(){
        Map<String,String> map = new HashMap<>();
        System.out.println(ssss(map.get("2")));
    }

    private String ssss(String a){
        if (a==null){
            return "s";
        }else {
            return "e";
        }
    }


    @Test
    public void erwei(){
        int[][] array = {
                {4,8,5,7},
                {1,2,0,4},
                {1,2,3,4},
                {1,2,0,4}};
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                if (array[i][j]==0){
                }
            }
        }
    }

}
