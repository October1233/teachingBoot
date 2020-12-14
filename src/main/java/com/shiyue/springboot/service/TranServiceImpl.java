package com.shiyue.springboot.service;


import com.shiyue.springboot.repository.TestMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


@Log4j
@Service
public class TranServiceImpl implements TranService{

    @Autowired
    TestMapper testMapper;

    private static final Jedis jedis = new Jedis("localhost");

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void ccac(){
        try {
            testMapper.updateName("八月","74");
            String s = null;
            s.toString();
            testMapper.updateaddr("平房","74");
        }catch (Exception e){
            log.error("嵌套回滚"+e.getMessage());
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void PROPAGATION_REQUIRED_B(){
        try {
            testMapper.updateaSex("后0","74");
            String s = null;
            s.toString();
            System.out.println("PROPAGATION_REQUIRED_B执行B");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void propagation_supports_B(){
        try {
            testMapper.updateaSex("后1","74");
            String s = null;
            s.toString();
            System.out.println("propagation_supports_B执行B");
            testMapper.updateName("后1","74");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public void propagation_mandatory_B(){
        try {
            testMapper.updateaSex("后2","74");
            String s = null;
            s.toString();
            System.out.println("propagation_mandatory_B执行B");
            testMapper.updateName("后2","74");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,timeout = 10)
    public void propagation_REQUIRES_NEW_B(){
        try {
            testMapper.updateaSex("后3","74");
            String s = null;
            s.toString();
            System.out.println("propagation_REQUIRES_NEW_B执行B");
        }catch (Exception e){
            log.error(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,timeout = 10)
    public void propagation_NESTED_B(){
        try {
            testMapper.updateaSex("后3","74");
            String s = null;
            s.toString();
            System.out.println("propagation_REQUIRES_NEW_B执行B");
        }catch (Exception e){
            log.error(e);
        }
    }

    public void propagation_NOT_SUPPORTED(){
        try {
            testMapper.updateaSex("后4","74");
            String s = null;
            s.toString();
            System.out.println("propagation_NOT_SUPPORTED_B执行B");
        }catch (Exception e){
            log.error(e);
        }
    }


    @Transactional(propagation = Propagation.NEVER,timeout = 10)
    public void propagation_NEVER(){
        try {
            testMapper.updateaSex("后5","74");
            String s = null;
            s.toString();
            System.out.println("propagation_REQUIRES_NEW_B执行B");
        }catch (Exception e){
            log.error(e);
        }
    }

    @Transactional(propagation = Propagation.NESTED,timeout = 10)
    public void propagation_NESTED(){
        try {
            testMapper.updateaSex("后6","74");
            String s = null;
            s.toString();
            System.out.println("propagation_NESTED_B执行B");
        }catch (Exception e){
            log.error(e);
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED,timeout = 10)
    public void READ_UNCOMMITTED(){
        try {
            String addr = testMapper.selectC("70").getAddress();
            jedis.set("num3",addr);
        }catch (Exception e){
            log.error(e);
        }
    }
}
