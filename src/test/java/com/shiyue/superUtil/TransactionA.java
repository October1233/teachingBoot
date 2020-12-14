package com.shiyue.superUtil;

import com.shiyue.springboot.SpringbootApplication;
import com.shiyue.springboot.repository.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class TransactionA {

    @Autowired
    private TestMapper testMapper;

    /**
     *1.propagation-required: 支持当前事务,如果有就加入当前事务中;如果当前方法没有事务,就新建一个事务;
     */
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void PROPAGATION_REQUIRED_A(){
        testMapper.updateaddr("前","74");
        System.out.println("PROPAGATION_REQUIRED_A执行A");
        TransactionB transactionB = new TransactionB();
        transactionB.PROPAGATION_REQUIRED_B();
        testMapper.updateName("后","74");

    }

}
