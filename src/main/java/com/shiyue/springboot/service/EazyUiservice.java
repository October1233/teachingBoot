package com.shiyue.springboot.service;

import com.shiyue.springboot.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Service
public interface EazyUiservice {

    public int insertUserService(String username, String sex, String address, String password, Date birthday);

    public Date formatData(String year, String month, String day) throws ParseException;

    Integer updateUserService(String username, String sex, String address, Date birthday, String id);

    public User findUserDataToId(String id);

    public List<User> allAddrReturn();

    @Transactional(readOnly = true)
    public List<User> findAll();

    public Integer insertInfoImp();

    public static void insertInto(){
        System.out.println("接口中静态方法");
    }
    default Integer insertInfo(){
        System.out.println("接口中默认方法");
        return null;
    }
    public boolean allStatus(String id);

}
