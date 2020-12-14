package com.shiyue.springboot.repository;



import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class EasyUiMapperProvider {



    public String findUserByInfo(int id,String name){
        BEGIN();
        SELECT("*");
        FROM("user");

        return SQL();
    }
}
