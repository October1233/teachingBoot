package com.shiyue.springboot.controller;

import com.shiyue.springboot.service.TestService;
import com.shiyue.springboot.service.TranService;
import com.shiyue.springboot.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/transactional")
public class TransactionalController {

    @Autowired
    TransactionalService transactionalService;

    @Autowired
    TranService tranService;

    @Autowired
    TestService testService;

    @ResponseBody
    @RequestMapping("/transactionalA")
    public void 普通的回滚(){
        transactionalService.updateService();
    }

    @ResponseBody
    @RequestMapping("/transactionalB")
    public void 普通的按键(){
        transactionalService.updateDaddy();
    }


    @ResponseBody
    @RequestMapping("/transactionalC")
    public void 普通的按键正确的(){
        transactionalService.updateDaddySuccess();
    }

    @ResponseBody
    @RequestMapping("/transactionalD")
    public void 普通的手动回滚(){
        transactionalService.updateDaddyHand();
    }

    @ResponseBody
    @RequestMapping("/required")
    public void required(){
        transactionalService.PROPAGATION_REQUIRED_A();
    }

    @ResponseBody
    @RequestMapping("/supports")
    public void supports(){
        //嵌套
        transactionalService.propagation_supports_A();
        //非嵌套
//        tranService.propagation_supports_B();
    }

    @ResponseBody
    @RequestMapping("/mandatory")
    public void mandatory(){
        //非异常
//        transactionalService.propagation_mandatory_A();
        //异常
        tranService.propagation_mandatory_B();
    }

    @ResponseBody
    @RequestMapping("/requiresnew")
    public void requires_new(){
        //前置
        transactionalService.propagation_REQUIRES_NEW_A();
        //可挂起
//        tranService.propagation_REQUIRES_NEW_B();
    }

    @ResponseBody
    @RequestMapping("/supported")
    public void supported(){
        transactionalService.propagation_NOT_SUPPORTED_A();
    }

    @ResponseBody
    @RequestMapping("/never")
    public void never(){
        transactionalService.propagation_NEVER();
    }

    @ResponseBody
    @RequestMapping("/nested")
    public void nested(){
        transactionalService.propagation_NESTED();
    }

    @ResponseBody
    @RequestMapping("/UNCOMMITTED")
    public void UNCOMMITTED(){
        transactionalService.UNCOMMITTED();
    }

    @ResponseBody
    @RequestMapping("/READ_COMMITTED")
    public void READ_COMMITTED(){
        transactionalService.READ_COMMITTED();
    }

    @ResponseBody
    @RequestMapping("/jedis")
    public void jedis(){
        transactionalService.jedisBut();
    }

    @ResponseBody
    @RequestMapping("/jedisOut")
    public void jedisOut(){
        transactionalService.jedisButOut();
    }


    @ResponseBody
    @RequestMapping("/REPEATABLE_READ")
    public void REPEATABLE_READ(){
        transactionalService.REPEATABLE_READ();
    }

}
