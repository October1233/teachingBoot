package com.shiyue.springboot.repository;


import com.shiyue.springboot.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    public User findUserById(@Param("id") String id);

    @Insert({"insert into user(username,sex,address,password)"+
            "value"+
            "(#{username},#{sex},#{address},#{password})"})

     public int saveUser(@Param("username") String username,
                         @Param("sex") String sex,
                         @Param("address") String address,
                         @Param("password") String password);

    @Update("update user set username=#{username},"+
            "birthday=#{birthday},"+
            "sex=#{sex},"+
            "address=#{address}"+
            "where id=#{id}")
     public void updataUser(User user);

    @Delete("delete from user where id=#{id}")
     public int deleteById(String id);

    @Select("select password from user where username=#{username}")
    public String findPwdToName(@Param("username") String username);

    @Select("select username from user")
    public List<String> getAllName();

    @Insert({"insert into user(username,password)"+
            "value"+
            "(#{username},#{password})"})
    public int registerInt(@Param("username")String username,
                           @Param("password")String password);
}
