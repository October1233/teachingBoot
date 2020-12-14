package com.shiyue.springboot.controller;

import com.shiyue.springboot.service.LoginService ;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class Logincontroller {
    private static Logger logger = LoggerFactory.getLogger(Logincontroller.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping("/welcome")
    private String loginto(){
        return "login";
    }
    @RequestMapping("/test2")
    private String testto2(){
        return "user/testsimple";
    }
    @RequestMapping("/eazyui")
    public String ezzyui(){
        return "user/newEazyUi";
    }
    @RequestMapping("/uregister")
    private String register(){
        logger.info("注册通道执行");
        return "register";
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping("/logup")
    @ResponseBody
    public Map<String, Object> Loginwin(HttpServletRequest request){
        logger.info("logincontroller sart");
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("u_name");
        String password = request.getParameter("u_pwd");
        Integer verify = loginService.LogService(username, password);
        logger.info("标志"+verify);
        map.put("return_id", verify);
        return map;
    }

    /**
     * 注册及验证
     * @param request
     * @return
     */
    @RequestMapping("/registerverify")
    @ResponseBody
    public JSONObject registerVerify(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        String fountUserName = request.getParameter("fountname");
        String fountPassWord = request.getParameter("fountpwd");
        String regexname = "^[a-z0-9_-]{3,15}$";
        String regexpwd = "^[a-zA-Z0-9]{6,16}$";
        logger.info("密码"+fountPassWord);
        logger.info("用户名"+fountUserName);
        if (fountUserName==null||fountUserName==""){
            jsonObject.put("unameStatus","2");//用户名为空
        }else if (!fountUserName.matches(regexname)){
            jsonObject.put("unameStatus","3");//用户名不合法
        }else {
            if (loginService.registerUnameCheck(fountUserName) == 1) {
                jsonObject.put("unameStatus", "1");//用户名已存在
            } else jsonObject.put("unameStatus", "0");//用户名可用
        }
        if (fountPassWord==null||fountPassWord==""){
            jsonObject.put("passwdStatus","2");
        }else if (!fountPassWord.matches(regexpwd)){
            jsonObject.put("passwdStatus","3");
        }else jsonObject.put("passwdStatus","0");

        if ((jsonObject.get("passwdStatus")=="0")&&(jsonObject.get("unameStatus")=="0")){
            Integer registerSta= loginService.registerIntService(fountUserName,fountPassWord);
            if (registerSta == 1){
                jsonObject.put("registerStatus","0");
            }else jsonObject.put("registerStatus","1");
        }
        logger.info("返回标志"+jsonObject);
        return jsonObject;
    }
}
