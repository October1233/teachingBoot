package com.shiyue.springboot.controller;


import com.shiyue.springboot.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.CycleDetectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map ajaxdelete(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("deleteid");
        Integer usercheck = userService.userDelete(id);
        logger.info("ajax删除数据启动"+"删除数据为"+id);
        if (id==null){
            jsonObject.put("userstate","请输入要删除的用户");
        }
        if (usercheck == 0)
        {
            jsonObject.put("userstate","未找到该用户，删除失败");
        }
        else {
            jsonObject.put("userstate","删除成功");
        }
        logger.info("删除数据标志位为"+jsonObject.get("userstate"));
        return jsonObject;
    }
    @RequestMapping("/insert")
    @ResponseBody
    public JSONObject ajaxinput(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("putuname");
        String sex = request.getParameter("putusex");
        String address = request.getParameter("putuaddr");
        String password = request.getParameter("putupwd");
        if(password.length()>=6 && password.length()<=10 ) {
            int flyuser =userService.userService(username, sex, address, password);
            logger.info("ajax输入数据"+flyuser+"即将返回"+username + " $ " + sex + " $ " + address+"ajax save...");
            if (flyuser>=1){
                jsonObject.put("return","插入成功");
            }
            else jsonObject.put("return","插入失败");
        }
        else jsonObject.put("return_pg",1);






        return jsonObject;
    }

    @RequestMapping("/ccccccc")
    @ResponseBody
    public void jsonObject(String parentCode, String type,HttpServletResponse response){
        List<Map<String,Object>> str = new ArrayList<>();
        System.out.println("一层"+parentCode+"----------"+type);
        Map<String,Object> map = new HashMap();
        map.put("code","1");
        map.put("name","aaa");
        str.add(map);
        Map<String,Object> map2 = new HashMap();
        map2.put("code","2");
        map2.put("name","bbb");
        str.add(map2);
        String fromObject = JSONArray.fromObject(str).toString();
        logger.info(fromObject);
        System.out.println(fromObject);
        renderString(response, fromObject, "text/json");
    }

    @RequestMapping("/ddddd")
    @ResponseBody
    public void sadsfasfa(String parentCode, String type,HttpServletResponse response){
        System.out.println("二层"+parentCode+"----------"+type);
    }





    @RequestMapping("/bbbb")
    @ResponseBody
    public void jsonObject1(String parentCode, String type,HttpServletResponse response){
        System.out.println("三层"+parentCode+"----------"+type);
        List<Map<String,Object>> str = new ArrayList<>();
        if ("1".equals(parentCode)){
            Map<String,Object> map = new HashMap();
            map.put("code","3");
            map.put("name","aaa---");
            str.add(map);
            Map<String,Object> map2 = new HashMap();
            map2.put("code","4");
            map2.put("name","bbb---");
            str.add(map2);
        }else if ("2".equals(parentCode)){
            Map<String,Object> map = new HashMap();
            map.put("code","5");
            map.put("name","aaa===");
            str.add(map);
            Map<String,Object> map2 = new HashMap();
            map2.put("code","6");
            map2.put("name","bbb===");
            str.add(map2);
        }
        String fromObject = JSONArray.fromObject(str).toString();
        logger.info(fromObject);
        System.out.println(fromObject);
        renderString(response, fromObject, "text/json");
    }


    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException var5) {
            return null;
        }
    }
}
