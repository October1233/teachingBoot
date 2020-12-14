package com.shiyue.springboot.service;

import com.shiyue.springboot.controller.EazyUiController;
import com.shiyue.springboot.domain.Students;
import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.EazyUiMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EazyUiServiceImpl implements EazyUiservice{


    final static int c1 = 30;

    private Logger logger = LoggerFactory.getLogger(EazyUiController.class);

    @Autowired
    EazyUiMapper eazyUiMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int insertUserService(String username, String sex, String address, String password, Date birthday){
        int mapperMark = eazyUiMapper.eazyUiInsertUser(username,sex,address,password,birthday);
        return mapperMark;
    }
    public List<User> findAll(){
        return eazyUiMapper.findAll();
    }

    public Date formatData(String year, String month, String day) throws ParseException {
        Date birthday = simpleDateFormat.parse(year+"-"+month+"-"+day);
        return birthday;
    }
    public Integer updateUserService(String username, String sex, String address, Date birthday,String id){
        Integer updateMark = eazyUiMapper.updateEazyUiUser(username,sex,address,birthday,id);
        return updateMark;
    }
    public User findUserDataToId(String id){
        logger.info(eazyUiMapper.findnamebyid(id));
        return eazyUiMapper.findUserToIdSql(id);
    }
    public List<User> allAddrReturn(){
        List<User> list = eazyUiMapper.allAddress();
        EazyUiservice.insertInto();
        return list;
    }
    public Integer insertInfoImp(){
        System.out.println("实现类的重写");
        BigDecimal bigDecimal = Integer::min;
        return bigDecimal.into1(1,5)+c1;
    }
    public List<User> users(){
        return null;
    }
    interface BigDecimal{
         Integer into1(int a,int b);
    }
    @Test
    public void testBDOptional(){
        User user = new User();
        List<User> userss = new ArrayList<>();
        user.setUsername("sssssyyyyy");
        userss.add(user);
        Map map = new HashMap();
        String ccc = "0.000270000";
        map.put("ccc1",ccc);
        java.math.BigDecimal muls = new java.math.BigDecimal("100");// 取100的倍数
        java.math.BigDecimal bigDecimal = new java.math.BigDecimal(String.valueOf(map.get("ccc1")));
        java.math.BigDecimal format = bigDecimal.multiply(muls);
        log.info("ccc{}nnn{}",format,bigDecimal);
        List<User> users = findAll();
        Optional<List<User>> OPUsers = Optional.ofNullable(users);
        OPUsers.orElse(userss);
        log.info("Optional打印{}",OPUsers.toString());

        List<Students> students = users.stream().map(user66 -> {
            Students students1 = new Students();
            students1.setName(user66.getUsername());

            return students1;
        }).collect(Collectors.toList());
        students.stream().forEach(System.out::println);
    }

    public boolean allStatus(String id){
        User user = new User();
        user.setUsername("ssss");
//        Predicate<String> checkId -> feeRuleDao.findRuleById(checkId).getStatus().equals(1)?true:false);
        BooleanMark booMark =checkId -> user.getUsername().equals(checkId)?true:false;
        booMark.logerBuffer(id);
        return booMark.checkId(id);
    }
    @FunctionalInterface
    interface BooleanMark{
        boolean checkId(String id);
        default void logerBuffer(String data){log.info("输入lambda的id为={}",data);}
    }
}
