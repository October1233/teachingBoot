package com.shiyue.springboot.service;

import com.shiyue.springboot.repository.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Slf4j
@Service
public class TransactionalServiceImpl implements TransactionalService{


    @Autowired
    TestMapper testMapper;

    @Autowired
    TranService tranService;

    private static final Jedis jedis = new Jedis("localhost");


    /**
     * 1、原子性(Atomicity)：事务中的全部操作在数据库中是不可分割的，要么全部完成，要么均不执行。
     *
     * 2、一致性(Consistency)：几个并行执行的事务，其执行结果必须与按某一顺序串行执行的结果相一致。
     *
     * 3、隔离性(Isolation)：事务的执行不受其他事务的干扰，事务执行的中间结果对其他事务必须是透明的。
     *
     * 4、持久性(Durability)：对于任意已提交事务，系统必须保证该事务对数据库的改变不被丢失，即使数据库出现故障。
     */


    @Transactional(
            propagation = Propagation.REQUIRED,//传播行为
            rollbackFor = Throwable.class,//指定回滚异常
            rollbackForClassName = "RuntimeException,NullPointerException",//指定多异常回滚
            noRollbackFor = Throwable.class,//指定不回滚异常
            noRollbackForClassName = "RuntimeException,NullPointerException",//指定多异常不回滚
            readOnly = false,//事务只读
            timeout = 10)//超时时间以秒
    public void text(){
        return;
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateService(){
        try {
            testMapper.updateName("一月","74");
            String s = null;
            s.toString();
            testMapper.updateaddr("普通回滚","74");
        }catch (Exception e){
            log.error("普通回滚",e);
            throw e;
        }

    }

    /**
     * 错误示范
     * 同一类中回滚
     */
    public void updateDaddy(){
        for (int i=72;i<75;i++) {
            ccac(i);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void ccac(int i){
            try {
                testMapper.updateName("十月",String.valueOf(i));
                String s = null;
                if (i==74){
                    s.toString();
                }
                testMapper.updateaddr("南岗",String.valueOf(i));
            }catch (Exception e){
                log.error("嵌套回滚"+e.getMessage());
                throw new RuntimeException();
            }
    }
    //正确
    public void updateDaddySuccess(){
        for (int i=72;i<75;i++) {
            tranService.ccac();
        }
    }




    //手动回滚

    /**
     * 有时我们需要捕获一些错误信息，又需要进行事务回滚，这时我们就需要用到Spring提供的事务切面支持类TransactionAspectSupport
     * 手动回滚事务一定要加上@Transactional，不然会报以下错误
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateDaddyHand(){
        for (int i=72;i<75;i++) {
            ccacHand(i);
        }
    }

    public void ccacHand(int i){
            try {
                testMapper.updateName("三月",String.valueOf(i));
                String s = null;
                log.info(String.valueOf(i));
                if (i==74){
                    s.toString();
                }
                testMapper.updateaddr("手动回滚",String.valueOf(i));
            }catch (Exception e){
                log.error("手动回滚"+e.getMessage());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }


        //1.加入异常
    @Transactional(propagation = Propagation.REQUIRED)
    public void PROPAGATION_REQUIRED_A(){
        try {
            testMapper.updateaddr("前0","74");
            System.out.println("PROPAGATION_REQUIRED_A执行A");
            tranService.PROPAGATION_REQUIRED_B();
            testMapper.updateName("前0","74");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


    /**
     * 2.propagation-supports: 支持当前事务,如果有就加入当前事务中;如果当前方法没有事务,就以非事务的方式执行;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_supports_A(){
        try {
            testMapper.updateaddr("前1","74");
            System.out.println("propagation_supports_A执行A");
            tranService.propagation_supports_B();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 3.propagation-mandatory: 支持当前事务,如果有就加入当前事务中;如果当前没有事务,就抛出异常;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_mandatory_A(){
        try {
            testMapper.updateaddr("前2","74");
            System.out.println("propagation_mandatory_B执行A");
            tranService.propagation_mandatory_B();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 4.propagation-requires_new: 新建事务,如果当前存在事务,就把当前事务挂起;如果当前方法没有事务,就新建事务;
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void propagation_REQUIRES_NEW_A(){
        try {
            testMapper.updateaddr("前3","74");
            tranService.propagation_REQUIRES_NEW_B();
            testMapper.updateName("前3","74");
//            String s = "";
//            s.substring(3);
            System.out.println("propagation_REQUIRES_NEW_B执行A");
        }catch (Exception e){
            log.error("错误"+e.getMessage());
            throw new RuntimeException();
        }
    }


    /**
     * 5.propagation-not-supported: 以非事务方式执行,如果当前方法存在事务就挂起当前事务;如果当前方法不存在事务,就以非事务方式执行;
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void propagation_NOT_SUPPORTED_A(){
        try {
            testMapper.updateaddr("前4","74");
            tranService.propagation_NOT_SUPPORTED();
            testMapper.updateName("前4","74");
            String s = "";
            s.substring(3);
            System.out.println("propagation_REQUIRES_NEW_B执行A");
        }catch (Exception e){
            log.error("错误"+e.getMessage());
            throw new RuntimeException();
        }
    }

    /**
     * 6.propagation-never: 以非事务方式执行,如果当前方法存在事务就抛出异常;如果当前方法不存在事务,就以非事务方式执行;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_NEVER(){
        try {
            testMapper.updateaddr("前5","74");
            tranService.propagation_NEVER();
            testMapper.updateName("前5","74");
//            String s = "";
//            s.substring(3);
            System.out.println("propagation_NEVER执行A");
        }catch (Exception e){
            log.error("错误"+e.getMessage());
            throw new RuntimeException();
        }
    }


    /**
     * 7.propagation-nested: 如果当前方法有事务,则在嵌套事务内执行;如果当前方法没有事务,则与required操作类似;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_NESTED(){
        try {
            testMapper.updateaddr("前6","74");
            tranService.propagation_NESTED();
            testMapper.updateName("前6","74");
//            String s = "";
//            s.substring(3);
            System.out.println("propagation_NESTED执行A");
        }catch (Exception e){
            log.error("错误"+e.getMessage());
            throw new RuntimeException();
        }
    }


    //读未提交
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_NESTED_A(){
        try {
            testMapper.updateaddr("前4","74");
//            String s = "";
//            s.substring(3);
            tranService.propagation_NESTED_B();
            testMapper.updateName("前4","74");
            System.out.println("propagation_NESTED_B执行A");
        }catch (Exception e){
            log.error("错误"+e.getMessage());
            throw new RuntimeException();
        }
    }

    //读未提交
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,propagation = Propagation.REQUIRED)
    public void UNCOMMITTED(){
        try {

            String addr = testMapper.selectC("70").getAddress();
            jedis.set("num2",addr);
            testMapper.updateaddr("2500","70");
            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            String s = null;
            s.toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
//    @Transactional(isolation=Isolation.READ_UNCOMMITTED)
//    public void jedisBut(){
//        String addr = testMapper.selectC("70").getAddress();
//        jedis.set("num3",addr);
//        try {
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String addrc = testMapper.selectAddr("70");
//        jedis.set("num4",addrc);
//    }
//
//    @Transactional(isolation=Isolation.READ_UNCOMMITTED)
//    public void jedisButOut(){
//        String addr = testMapper.selectC("70").getAddress();
//        jedis.set("num4",addr);
//    }


    //读已提交
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void READ_COMMITTED(){
        try {
            String addr = testMapper.selectC("70").getAddress();
            jedis.set("num2",addr);
            testMapper.updateaddr("2000","70");
            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
//            cc();
//            testMapper.updateaddr("2002","69");
//            String s = null;
//            s.toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
//    @Transactional(isolation=Isolation.READ_COMMITTED)
//    public void jedisBut(){
//        String addr = testMapper.selectC("70").getAddress();
//        jedis.set("num3",addr);
//        try {
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        String addrc = testMapper.selectAddr("70");
//        jedis.set("num4",addrc);
//    }
//    @Transactional(isolation=Isolation.READ_COMMITTED)
//    public void jedisButOut(){
//        String addr = testMapper.selectC("70").getAddress();
//        jedis.set("num4",addr);
//    }

    //可重复度
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void REPEATABLE_READ(){
        try {
            String addr = testMapper.selectC("70").getAddress();
            jedis.set("num2",addr);
            testMapper.updateaddr("1000","70");
            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
//            String s = null;
//            s.toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Transactional(isolation=Isolation.REPEATABLE_READ)
    public void jedisBut(){
        String addr = testMapper.selectC("70").getAddress();
        jedis.set("num3",addr);
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        String addrc = testMapper.selectAddr("70");
        jedis.set("num4",addrc);
    }

    @Transactional(isolation=Isolation.REPEATABLE_READ)
    public void jedisButOut(){
        String addr = testMapper.selectC("70").getAddress();
        jedis.set("num4",addr);
    }
    @Test
    public void jedis2(){
        System.out.println("执行前"+jedis.get("num2"));
        System.out.println("提交前"+jedis.get("num3"));
        System.out.println("提交后"+jedis.get("num4"));
    }

    public void clect(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("com.mysql.cj.jdbc.Driver","user","password");

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            System.out.println("执行一些操作");

            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException sql){
                System.err.println("sql"+sql);
            }
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (SQLException sql){
                    System.err.println("sql"+sql);
                }
            }
        }
    }


}
