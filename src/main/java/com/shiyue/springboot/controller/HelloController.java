package com.shiyue.springboot.controller;

import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.service.UserService ;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/user")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/input")
    public void save(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        userService.userService(username, sex, address, password);
        logger.info("普通插入数据"+username + " " + sex + " " + address+"controller save...");
    }
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request) throws Exception {
        String id = request.getParameter("dlid");
        userService.userDelete(id);
        logger.info(id+"被普通删除");
    }
    @RequestMapping(value = "/findbyid")
    @ResponseBody
    public JSONObject userDataId(HttpServletRequest request) throws Exception {
        String userData = request.getParameter("findid");
        User user = userService.userFindById(userData);
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy年MM月dd日 " );
        JSONObject jsonUser =new JSONObject();
        if (userService.userFindById(userData) == null){
            jsonUser.put("msg",1);
        }
        else {
            String str = sdf.format(user.getBirthday());
            jsonUser = JSONObject.fromObject(user);
            jsonUser.put("birthday",str);
            jsonUser.put("msg",0);
        }
        return jsonUser;
    }



//    @RequestMapping("/findall")
//    public List<User> userDataAll(){
//        List<User> list =
//    }
}
