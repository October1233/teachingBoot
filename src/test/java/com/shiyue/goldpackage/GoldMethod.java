package com.shiyue.goldpackage;

import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GoldMethod {

    Supplier<Map> mapSupplier = HashMap::new;

    @Autowired
    UserMapper userMapper;

    public boolean allStatus(String id)
    {
        return checkStatusByFunction(Integer.parseInt(userMapper.findUserById(id).getSex()),status->status==1);
    }
    private boolean checkStatusByFunction(int value, Predicate<Integer> predicate)
    {
        return predicate.test(value);
    }





}
