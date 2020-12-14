package com.shiyue.springboot.service;


import com.shiyue.springboot.domain.User ;
import java.io.IOException;
import java.util.List;

public interface UserService {
//    void save();

    public int userService(String username, String sex, String address, String password) throws Exception;
    public int userDelete(String id) throws IOException, Exception;
    public User userFindById(String id) throws Exception;
    public List<User> userFindAll();
}
