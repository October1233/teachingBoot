package com.shiyue.studybass;

import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;



//实现Callable接口，call()方法可以有返回结果
public class ThreadC implements Callable<User> {

    @Autowired
    TestMapper testMapper;

    @Override
    public User call() throws Exception {
        User user = testMapper.selectC("3");
        try {//模拟任务，执行了500毫秒；
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}