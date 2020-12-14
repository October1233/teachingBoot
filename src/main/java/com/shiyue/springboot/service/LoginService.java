package com.shiyue.springboot.service;

public interface LoginService {
    public Integer LogService(String username, String password);
    public Integer registerUnameCheck(String registname);
    public Integer registerIntService(String username,String password);
}
