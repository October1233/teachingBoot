package com.shiyue.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "user")
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private int age;
    private String sex;
    private String address;
    private String password;
    private String surepassword;
    private String formatDate;
    private BigDecimal amount;
    private List<School> schools;
    private ScreenConditions screenConditions;
    



}
