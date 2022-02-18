package com.shiyue.springboot.repository;

import com.shiyue.springboot.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TestMapper {


    @Insert({"insert into c1(column_1)"+
            "values"+
            "(#{column_1})"})
    public Integer IntStr(@Param("column_1") String column_1);

    @Select("select username from user where password=#{password}")
    public List<String> nameToUser(@Param("password") String password);

    @Update("update user set sex='中' where username=#{username}")
    public Integer changeuser(String username);

    @Select("select * from user where id=#{id}")
    public User selectC(String id);

    @Select("select address from user where id=#{id}")
    public String selectAddr(String id);

    @Update("update user set username =#{username} where id = #{id}")
    public Integer updateName(String username,String id);

    @Update("update user set address =#{address} where id = #{id}")
    public Integer updateaddr(String address,String id);

    @Update("update user set sex =#{sex} where id = #{id}")
    public Integer updateaSex(String sex,String id);

//    @Select("select parent_id,id from area_localhost")
    public List<Map<String,Object>> selectaddCode();


}
