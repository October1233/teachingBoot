package com.shiyue.springboot.repository;

import com.shiyue.springboot.domain.School;
import com.shiyue.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


public interface EazyUiMapper {

    //eazyui展示sql
    @Select("select * from user")
    public List<User> findAll();

    //eazyui测试界面一的插入数据Sql
    @Insert({"insert into user(username,sex,address,password,birthday)"+
            "value"+
            "(#{username},#{sex},#{address},#{password},#{birthday})"})
    public int eazyUiInsertUser(@Param("username") String username,
                                @Param("sex") String sex,
                                @Param("address") String address,
                                @Param("password") String password,
                                @Param("birthday") Date birthday);
    //eazyui测试页一的更改数据sql
    @Update("update user set "+
            "username=#{username},"+
            "birthday=#{birthday},"+
            "sex=#{sex},"+
            "address=#{address}"+
            "where id=#{id}")
    public Integer updateEazyUiUser(@Param("username") String username,
                                    @Param("sex") String sex,
                                    @Param("address") String address,
                                    @Param("birthday") Date birthday,
                                    @Param("id") String id);
    //修改数据验证是否更改用户姓名sql
    @Select("select username from user where id=#{id}")
    String findnamebyid(String id);

    //根据id搜寻用户数据
    @Select("select * from user where id = #{id}")
    User findUserToIdSql(String id);

    //
    @Select("select address from user group by address")
    public List<User> allAddress();


    @Insert("insert into school(name,class_id) values(#{name},#{class_id})")
    public List<School> intoSchool(@Param("name") String name,
                                   @Param("class_id") String class_id);

    @SelectProvider(type = EasyUiMapperProvider.class,method = "findUserByInfo")
    public List<User> findUserByInfo(@Param("id") int id,@Param("name") String name);
}
