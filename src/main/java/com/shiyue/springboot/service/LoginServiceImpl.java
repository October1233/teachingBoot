package com.shiyue.springboot.service;

import com.shiyue.springboot.controller.Logincontroller;
import com.shiyue.springboot.repository.UserMapper ;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    private static Logger logger = LoggerFactory.getLogger(Logincontroller.class);

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private UserMapper userMapper;


    public Integer LogService(String username, String password){
        UserMapper userDao =sqlSession.getMapper(UserMapper.class);
        String findPwd = userDao.findPwdToName(username);
        Integer yanzheng=null;
        logger.info("根据用户名取回的密码为："+findPwd);
        logger.info("输入的密码为："+password);
        if(findPwd==password)
        {
            yanzheng = 0;
        }else
        {
            yanzheng = 1;
        }
        return yanzheng;
    }
    public Integer registerUnameCheck(String registname){
        List<String> unameList = userMapper.getAllName();
        Integer ProofMark = 0;
        for (String userName:unameList){
            if(userName.equals(registname))
            {
                ProofMark = 1;
                break;
            }
            else{
                ProofMark = 0;
            }
        }

        return ProofMark;
    }
    public Integer registerIntService(String username,String password){
        Integer registerStatus = userMapper.registerInt(username,password);
        return registerStatus;
    }

}
