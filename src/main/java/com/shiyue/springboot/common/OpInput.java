package com.shiyue.springboot.common;

import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.EazyUiMapper;
import com.shiyue.springboot.service.EazyUiservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OpInput {

    @Autowired
    EazyUiservice eazyUiservice;

    @Test
    public void testOp() {
        List<User> user = eazyUiservice.findAll();
    }
}
