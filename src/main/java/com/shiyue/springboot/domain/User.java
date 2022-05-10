package com.shiyue.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(address, user.address) &&
                Objects.equals(password, user.password) &&
                Objects.equals(surepassword, user.surepassword) &&
                Objects.equals(formatDate, user.formatDate) &&
                Objects.equals(amount, user.amount) &&
                Objects.equals(schools, user.schools) &&
                Objects.equals(screenConditions, user.screenConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, birthday, age, sex, address, password, surepassword, formatDate, amount, schools, screenConditions);
    }
}
