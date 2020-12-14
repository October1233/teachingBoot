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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/newDatagrid")
public class NewEazyUiController {

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

    @RequestMapping("/showAllData")
    @ResponseBody
    public JSONArray allUser() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<User> allUserlist = eazyUiMapper.findAll();
//        allUserlist.stream().forEach(user -> System.out.println("排序前-"+user.getId()));
        List<User> ace = allUserlist.stream().sorted(Comparator.comparing(User::getId).
                reversed()).collect(Collectors.toList());
//        ace.stream().forEach(user -> System.out.println("排序后"+user.getId()));
        for (int i = 0; i < allUserlist.size(); i++) {
            allUserlist.get(i).setFormatDate(simpleDateFormat.format
                    (allUserlist.get(i).getBirthday()));
        }
        JSONArray jsonArray = JSONArray.fromObject(ace);
        return jsonArray;
    }


    /**
     * 更改新数据

     * @return
     * @throws ParseException
     */
    @RequestMapping("/timer")
    @ResponseBody
    public JSONObject timer(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cc","success");
        logger.info("进入timer",jsonObject);
        try {
            Thread.sleep(6000);
        }catch (Exception e){
            logger.error("超时",e);
        }
        return jsonObject;
    }


    /**
     * 更改新数据
     * @param request
     * @param user
     * @return
     * @throws ParseException
     */
    @RequestMapping("/newUpdateUser")
    @ResponseBody
    public JSONObject newUpdateUserInfo(HttpServletRequest request, User user) throws ParseException {
        logger.info("新信息更改启用");
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
        String username = request.getParameter("updatausername");
        String sex = request.getParameter("updatasex");
        String address = request.getParameter("updataaddress");
        String birthday = request.getParameter("updatabirthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formatbirthday = simpleDateFormat.parse(birthday);
        logger.info("数据类型转换后"+formatbirthday);
        String id = request.getParameter("uid");
        Integer controllerUpdateMark = eazyUiservice.updateUserService(username, sex, address, formatbirthday, id);
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
    /**
     * 新插入EazyUi对象
     */
    @RequestMapping("/newInsertUserInfo")
    @ResponseBody
    public JSONObject newInsertUserInfo(HttpServletRequest request,User user) throws ParseException {
        logger.info("前端字节流"+user);
        JSONObject newjsonObject = new JSONObject();
        if (request == null) {
            newjsonObject.put("code", "failed");
            newjsonObject.put("msg", "请输入用户信息");
            return newjsonObject;
        }
        if (loginService.registerUnameCheck(request.getParameter("newUsername")) == 1) {
            newjsonObject.put("code", "failed");
            newjsonObject.put("msg", "该用户名已存在");
            return newjsonObject;
        }
        if (!request.getParameter("newPassword").equals(request.getParameter("newSurePassword"))) {
            newjsonObject.put("code", "failed");
            newjsonObject.put("msg", "两次密码输入不一致");
            return newjsonObject;
        }
        String newUsername = request.getParameter("newUsername");
        String newGender = request.getParameter("newGender");
        String newAddress = request.getParameter("newAddress");
        String newPassword = request.getParameter("newPassword");
        String newBirthday = request.getParameter("newBirthday");
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newFormatbirthday = newSimpleDateFormat.parse(newBirthday);
        int newInsertMark = eazyUiservice.insertUserService(newUsername, newGender, newAddress, newPassword, newFormatbirthday);
        if (newInsertMark == 1) {
            newjsonObject.put("code", "success");
            newjsonObject.put("msg", "新增成功");
            return newjsonObject;
        } else {
            newjsonObject.put("code", "fail");
            newjsonObject.put("msg", "新增失败！");
            return newjsonObject;
        }
    }

    /**
     * 搜寻用户数据
     */
    @RequestMapping("/finduser")
    @ResponseBody
    public JSONObject findUserDataToId(HttpServletRequest request) {
        String returnID = request.getParameter("allDataId");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        logger.info("传入数据"+returnID);
        User user = eazyUiservice.findUserDataToId(returnID);
        if (user!=null) {
            user.setFormatDate(simpleDateFormat.format(user.getBirthday()));
            JSONObject jsonObject = JSONObject.fromObject(user);
            logger.info("数据链   " + jsonObject);
            return jsonObject;
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg",1);
            return jsonObject;
        }
    }

    /**
     * 新easyUI的地点选择
     * @return
     */
    @RequestMapping("/newCombobox")
    @ResponseBody
    public String newAddress(){
        List<User> list = eazyUiservice.allAddrReturn();
        List<User> addr = list.stream().sorted
                (Comparator.comparing(User::getAddress)).distinct().collect(Collectors.toList());
        addr.stream().forEach(user -> System.out.println("排序后+"+user.getAddress()));
        String jsonObject = JSONArray.fromObject(addr).toString();
        return jsonObject;
    }
}
