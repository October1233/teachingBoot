package com.shiyue.springboot.service;

import com.shiyue.springboot.repository.EazyUiMapper;
import com.shiyue.springboot.repository.UserMapper;
import com.shiyue.springboot.domain.User ;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    EazyUiMapper eazyUiMapper;

    public int userService(String username, String sex, String address, String password) throws Exception {
        UserMapper userDao=sqlSession.getMapper(UserMapper.class);
        int flyuser = userDao.saveUser(username,sex,address,password);
        return flyuser;
    }

    public User userFindById(String id) throws Exception {
        User findUser = userMapper.findUserById(id);
        return findUser;
    }

    public List<User> userFindAll() {
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> findAllUser = eazyUiMapper.findAll();
        sqlSession.close();
        return findAllUser;
    }



    public int userDelete(String id) throws Exception {
        Integer usercheck = 0;
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        int userdelete = userDao.deleteById(id);
        return userdelete;
    }
}

