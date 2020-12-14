package com.shiyue.springboot.service.job;

import com.shiyue.springboot.domain.School;
import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.EazyUiMapper;
import com.shiyue.springboot.service.EazyUiservice;
import com.shiyue.springboot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TimedTask {

    @Autowired
    EazyUiservice eazyUiservice;

    @Autowired
    TestService testService;

    @Autowired
    EazyUiMapper eazyUiMapper;

    private Logger logger = LoggerFactory.getLogger(TimedTask.class);
    Map map = new HashMap();


    @Scheduled(cron = "0 0/2 * * * ?")
    public void sssssk(){
        testService.sdasd();
    }
    @Scheduled(cron = "0 1/2 * * * ?")
    public void sssss2(){
        testService.shsdaaa();
    }



    @Scheduled(cron = "0 0/30 * * * ?")
    public void aotuputjob(){
        String ccc = "0.000270000";
        map.put("ccc1",ccc);


        String string = new String("100");
        logger.info(string);

        BigDecimal muls = new BigDecimal("100");// 取100的倍数
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(map.get("ccc1")));
        logger.info(bigDecimal.multiply(muls)+"");
        BigDecimal format = bigDecimal.multiply(muls);


        List<User> users1 = null;
        List<User> users = new ArrayList<>();
        List<User> users2 = eazyUiservice.findAll();
        String a = "";
        String b = "";
//        users2.add(a);
//        users2.add(b);
        Optional<List<User>> userOpt2 = Optional.ofNullable(users2);
        Optional<List<User>> userOpt1 = Optional.ofNullable(users1);
        Optional<List<User>> userOpt = Optional.ofNullable(users);
        System.out.println("对象s"+userOpt.toString());
        System.out.println("格式对象1"+userOpt1);
        System.out.println("格式对象1"+userOpt2);
        System.out.println("对象1"+users1);
        System.out.println("对象2"+users2);
        System.out.println(userOpt1.orElse(users));
        System.out.println(StringUtils.isBlank(a));



        System.out.println("对象打印"+users2.toString()+users2.get(0).getAddress());
        users2.forEach(user -> {
            System.out.println(user.getAddress());
        });
        users2.stream().forEach(user -> {
            user.setAddress("未知");
            int i = 0;
            System.out.println(i+"="+user.getAddress());
            i++;

        });
        System.out.println(eazyUiservice.insertInfoImp());
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void sssss(){
        List<String> list = new ArrayList<>();
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt.ok");
        list.add("credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt");
        list.add(null);
        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt.ok");
        for (String s:list){
            try{
                sssfff(s);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void sssfff(String ccccc) throws IndexOutOfBoundsException{
        System.out.println(ccccc.toString());
    }


}
