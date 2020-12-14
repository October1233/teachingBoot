package com.shiyue.superUtil;

import com.shiyue.springboot.repository.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMapper.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionB {

    @Autowired
    private TestMapper testMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void PROPAGATION_REQUIRED_B(){
        testMapper.updateaSex("加入事务","74");
        System.out.println("PROPAGATION_REQUIRED_B执行B");
    }
}
