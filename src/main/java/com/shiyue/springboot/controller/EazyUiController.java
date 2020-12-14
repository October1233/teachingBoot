package com.shiyue.springboot.controller;


import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.EazyUiMapper;

import com.shiyue.springboot.service.EazyUiservice;
import com.shiyue.springboot.service.LoginService;

import com.shiyue.springboot.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/datagrid")
public class EazyUiController {

    private Logger logger = LoggerFactory.getLogger(EazyUiController.class);
    private static String regex = "^(?![A-Za-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$";


    @Autowired
    EazyUiservice eazyUiservice;

    @Autowired
    EazyUiMapper eazyUiMapper;

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;






    /**
     * 显示数据
     *
     * @return
     */



    @RequestMapping("/showAllData")
    @ResponseBody
    public JSONArray allUser() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<User> allUserlist = eazyUiMapper.findAll();
        User user = new User();



        for (int i = 0; i < allUserlist.size(); i++) {
            Date birDate = allUserlist.get(i).getBirthday();

            String newBirthday = simpleDateFormat.format(birDate);
            allUserlist.get(i).setFormatDate(newBirthday);
        }
        JSONArray jsonArray = JSONArray.fromObject(allUserlist);
        return jsonArray;
    }

    /**
     * eazyui插入校验
     *
     * @param request
     * @param user
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public JSONObject insertUser(HttpServletRequest request, User user) throws ParseException {
        JSONObject jsonObj = new JSONObject();
        if (user == null) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "请输入用户信息");
            return jsonObj;
        }
        if (loginService.registerUnameCheck(user.getUsername()) == 1) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "该用户名已存在");
            return jsonObj;
        }
        if (!user.getPassword().equals(user.getSurepassword())) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "两次密码输入不一致");
            return jsonObj;
        }
        String stryear = request.getParameter("year");
        String strmonth = request.getParameter("month");
        String strday = request.getParameter("day");
        if (2020 < Integer.parseInt(stryear) || 1900 > Integer.parseInt(stryear)) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "请输入正确年份");
            return jsonObj;
        }
        if (13 < Integer.parseInt(strmonth) || 0 > Integer.parseInt(strmonth)) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "请输入正确月份");
            return jsonObj;
        }
        if (0 > Integer.parseInt(strday) || 32 < Integer.parseInt(strday)) {
            jsonObj.put("code", "failed");
            jsonObj.put("msg", "请输入正确日期");
            return jsonObj;
        }
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        Date birthday = eazyUiservice.formatData(year, month, day);
        logger.info("合成后生日" + birthday);
        int mark = eazyUiservice.insertUserService(username, sex, address, password, birthday);
        if (mark == 1) {
            jsonObj.put("code", "success");
            jsonObj.put("msg", "新增成功");
            return jsonObj;
        } else {
            jsonObj.put("code", "fail");
            jsonObj.put("msg", "新增失败！");
            return jsonObj;
        }
    }

    /**
     * 格式化字符串时间
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping("testdata")
    public String dataed() throws ParseException {
        Date data = eazyUiservice.formatData("1996", "10", "27");
        logger.info("data" + data);
        return null;
    }

    /**
     * 根据前端选中id删除数据
     *
     * @param request
     * @return 数据库受影响行数
     * @throws Exception
     */
    @RequestMapping("/eazyUiDelete")
    @ResponseBody
    public int deleteForId(HttpServletRequest request) throws Exception {
        int deleteMark = userService.userDelete(request.getParameter("id"));
        if (deleteMark == 1) {
            logger.info("删除成功");
        }
        return deleteMark;
    }

    @RequestMapping("/upDataUser")
    @ResponseBody
    public JSONObject updateEUUser(HttpServletRequest request) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        if (!request.getParameter("updatausername").equals(eazyUiMapper.findnamebyid(request.getParameter("uid")))) {
            logger.info("前端的名字" + request.getParameter("updatausername") + "库里对应的名字" +
                    eazyUiMapper.findnamebyid(request.getParameter("uid")));
            if (loginService.registerUnameCheck(request.getParameter("updatausername")) == 1) {
                jsonObject.put("code", "failed");
                jsonObject.put("msg", "该用户名已存在");
                return jsonObject;
            }
        }
        String stryear = request.getParameter("updatayear");
        String strmonth = request.getParameter("updatamonth");
        String strday = request.getParameter("updataday");
        if (2020 < Integer.parseInt(stryear) || 1900 > Integer.parseInt(stryear)) {
            jsonObject.put("code", "failed");
            jsonObject.put("msg", "请输入正确年份");
            return jsonObject;
        }
        if (13 < Integer.parseInt(strmonth) || 0 > Integer.parseInt(strmonth)) {
            jsonObject.put("code", "failed");
            jsonObject.put("msg", "请输入正确月份");
            return jsonObject;
        }
        if (0 > Integer.parseInt(strday) || 32 < Integer.parseInt(strday)) {
            jsonObject.put("code", "failed");
            jsonObject.put("msg", "请输入正确日期");
            return jsonObject;
        }
        String username = request.getParameter("updatausername");
        String sex = request.getParameter("updatasex");
        String address = request.getParameter("updataaddress");
        String year = request.getParameter("updatayear");
        String month = request.getParameter("updatamonth");
        String day = request.getParameter("updataday");
        String id = request.getParameter("uid");
        Date birthday = eazyUiservice.formatData(year, month, day);
        Integer controllerUpdateMark = eazyUiservice.updateUserService(username, sex, address, birthday, id);
        if (controllerUpdateMark == 1) {
            jsonObject.put("code", "success");
            jsonObject.put("msg", "更改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", "fail");
            jsonObject.put("msg", "更改失败！");
            return jsonObject;
        }
    }
    private void putin(){
        System.out.println("输出");
    }
    public void adcf(){
        List<User> users = eazyUiMapper.findAll();
        users.stream();
        eazyUiservice.insertInfo();
        EazyUiservice.insertInto();
        users.forEach(user -> user.getAddress());

    }
    @RequestMapping("/cctest")
    @ResponseBody
    public JSONObject selectInfo(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String a = "success"+ request.getParameter("addr");
        logger.info("反相触发"+a);
        jsonObject.put("rtaddr",a);
        return jsonObject;
    }
}
