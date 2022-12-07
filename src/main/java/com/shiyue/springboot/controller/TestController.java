package com.shiyue.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.domain.UserStady;
import com.shiyue.springboot.repository.EazyUiMapper;
import com.shiyue.springboot.repository.TestMapper;

import com.shiyue.springboot.service.TestService;
import com.shiyue.springboot.service.TestServiceImpl;
import com.shiyue.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;


@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    TestMapper testMapper;

    @Autowired
    EazyUiMapper eazyUiMapper;


    @Autowired
    UserService userService;

    private Random random = new Random(System.currentTimeMillis());


    @RequestMapping(value = "/ajaxmap")
    @ResponseBody
    public Map ajaxtest(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","成功");
        return jsonObject;
    }



    @RequestMapping("/c1")
    public String text1() throws Exception {

        File zf = new File("D:/CUSTBALCHK_0010_20190914_001.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(zf));
        String c1;
        while((c1 = bufferedReader.readLine())!=null){
            String[] c2 = c1.split("&;");
            Integer val = testMapper.IntStr(c2[0]);
        }
        return null;
    }
    @RequestMapping("/str")
    public void testStr(){
        List<String> username = testMapper.nameToUser("123");
        for(String name:username)
      {
            testMapper.changeuser(name);
        }
        System.out.println(username.size());
        System.out.println(username);

    }
    @RequestMapping("testcc")
    public void ListCc(){
        List<UserStady> list = new ArrayList();
    }

    @RequestMapping("render")
    @ResponseBody
    public Map<Object, String> render(@RequestParam("rendercc") String renderup){
        Map jo = new HashMap();

        JSONObject jsonObject = new JSONObject();
        jo.put("rendermark",renderup);
        List<UserStady> list = new ArrayList<>();
        UserStady userStady = new UserStady();
        userStady.setClassroom("1");
        userStady.setSchool("清华");
        userStady.setTeacher("zhaoying");
        userStady.setPeoNum(40);
        logger.info("usermothed=="+userStady);
        list.add(userStady);
        logger.info("list=="+list);
        jo.put("userStady",list);
        logger.info("jo_map=="+jo);
        logger.info("getListToMap==",jo.get(list));
        return jo;
    }
    @RequestMapping("/testArray")
    @ResponseBody
    public void testArray(){
        JSONArray jsonArray = new JSONArray();
        logger.info("jsonArray=="+jsonArray);
        List list = new ArrayList();
        list.add("1");
        list.add("清华");
        list.add("zhaoying");
        list.add(40);
        logger.info("list=="+list);
        List<UserStady> userList = new ArrayList<>();
        UserStady userStady1 = new UserStady();
        UserStady userStady2 = new UserStady();
        userStady1.setClassroom("3");
        userStady1.setSchool("北大");
        userStady1.setTeacher("liangxiaofeng");
        userStady1.setPeoNum(35);
        userStady2.setClassroom("1");
        userStady2.setSchool("清华");
        userStady2.setTeacher("zhaoying");
        userStady2.setPeoNum(40);
        userList.add(userStady2);
        userList.add(userStady1);
        logger.info("UserList=="+userList);
    }
    @RequestMapping("/testMapToMothed")
    @ResponseBody
    public void testMap(){
        List list = new ArrayList();
        UserStady userStady1 = new UserStady();
        Map<String,UserStady> map = new HashMap();
        userStady1.setClassroom("3");
        userStady1.setSchool("北大");
        userStady1.setTeacher("liangxiaofeng");
        userStady1.setPeoNum(35);
        map.put("List",userStady1);
        logger.info("map=="+map);
        logger.info("map.get(list)=="+map.get("List").getTeacher());
    }
    @RequestMapping("/testMapToListToMothed")
    @ResponseBody
    public void testMapList(){
        List<UserStady> list = new ArrayList();
        UserStady userStady1 = new UserStady();
        userStady1.setClassroom("3");
        userStady1.setSchool("北大");
        userStady1.setTeacher("liangxiaofeng");
        userStady1.setPeoNum(35);
        list.add(userStady1);
        Map<String,List<UserStady>> map = new HashMap();
        map.put("userMapList",list);
        String teacherName = map.get("userMapList").get(0).getTeacher();
        logger.info("MapNAME=="+teacherName);
    }
    @RequestMapping("/arrayListAndJsonArray")
    @ResponseBody
    public void testArrayAndJson(){
        Map map = new HashMap();
        JSONArray jsonArray = new JSONArray();
        List list = new ArrayList();
        list.add("1");
        list.add("清华");
        list.add("zhaoying");
        list.add(40);
        List<UserStady> uslist = new ArrayList<>();
        UserStady userStady1 = new UserStady();
        UserStady userStady2 = new UserStady();
        userStady1.setClassroom("2");
        userStady1.setSchool("北大2");
        userStady1.setTeacher("liangxiaofeng2");
        userStady1.setPeoNum(352);
        uslist.add(userStady1);
        userStady2.setClassroom("2");
        userStady2.setSchool("北大2");
        userStady2.setTeacher("liangxiaofeng2");
        userStady2.setPeoNum(352);
        uslist.add(userStady2);
        map.put("cd",uslist);
//        logger.info("jsonArray="+jsonArray);
//        logger.info("list="+list);
//        logger.info("uslist="+uslist);
        logger.info(""+map);
        logger.info(""+uslist.size());
    }


    @RequestMapping("/allJson")
    public void allJson(){
        List<User> allUserlist = eazyUiMapper.findAll();
        for (int i=0;i<allUserlist.size();i++){
            Date birDate = allUserlist.get(i).getBirthday();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月DD日");
            SimpleDateFormat simpleDateFormat1=null;
            String newBirthday = simpleDateFormat.format(birDate);
            allUserlist.get(i).setFormatDate(newBirthday);
            logger.info("date"+newBirthday);
        }
        logger.info("List"+allUserlist.get(0).getFormatDate());
    }
    @RequestMapping("/zhengze")
    @ResponseBody
    public void allJson(HttpServletRequest request){
        Map map = new HashMap();
        Map<String,Object> questionMap = new HashMap<>();
        List<Map<String,Object>> questionList = new ArrayList();

        for (User user:eazyUiMapper.findAll()){
           questionMap.put("addr",user.getAddress());
           questionMap.put("name",user.getUsername());
           questionMap.put("sex",user.getSex());
           questionList.add(questionMap);
        }
        map.put("cd",questionList);
        String a="123";
        Integer formatType = Integer.parseInt(a);
        logger.info(formatType+"");
        String b = "%"+a+"%";
        logger.info(""+b);
        String c = null;
        if (b.equals("%123%")){
            c = "234";
        }
        logger.info("cc"+c);
        String dd = "1.0E+4";
        BigDecimal cc = new BigDecimal(dd);
        System.out.println(cc);
    }

    @PostMapping("/lambda")
    @ResponseBody
    public void testA(){
        final Integer c = 12;
        Jiaziduan jiaziduan = a -> System.out.println(a + c);
        jiaziduan.sayName(2);
    }
    interface Jiaziduan{
        void sayName(Integer str);
    }


    @Test
    public void test2() throws Exception {
        System.out.println("main函数开始执行");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
                return 3;
            }
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        System.out.println("main函数执行结束");
    }


    private List<User> thread(String i){
        List<User> users = new ArrayList<>();
        new Thread(()-> {
            User user = testMapper.selectC(String.valueOf(i));
        }).start();

        return users;
    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();



        // 等凉菜
        Callable ca1 = new Callable(){

            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "凉菜准备完毕";
            }
        };
        FutureTask<String> ft1 = new FutureTask<String>(ca1);
        new Thread(ft1).start();

        // 等包子 -- 必须要等待返回的结果，所以要调用join方法
        Callable ca2 = new Callable(){

            @Override
            public Object call() throws Exception {
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "包子准备完毕";
            }
        };
        FutureTask<String> ft2 = new FutureTask<String>(ca2);
        new Thread(ft2).start();

        System.out.println(ft1.get());
        System.out.println(ft2.get());

        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间："+(end-start));
    }

    @RequestMapping("/optional")
    @ResponseBody
    public void sssss(){
        List<String> list = new ArrayList<>();
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt.ok");
        list.add("credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt");
        list.add(null);
        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt.ok");
        for (String s:list){
            try{
                sssfff(s);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void sssfff(String ccccc) throws IndexOutOfBoundsException{
        System.out.println(ccccc.toString());
    }
//    public void optional(){
//        long startTime=System.nanoTime();
//        System.out.println("执行代码块/方法");
////        System.out.println(thread());
//        List<User> users = new ArrayList<>();
//        for(int i = 35 ; i<76 ; i++){
//            Callable ca1 = new Callable(){
//                @Override
//                public User call() throws Exception {
//                    User user = null;
////                    try {
//////                        user = testMapper.selectC(String.valueOf(i));
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                    return user;
//                }
//            };
//            FutureTask<String> ft1 = new FutureTask<String>(ca1);
//            new Thread(ft1).start();
//
//        }
//        long endTime=System.nanoTime();
//        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
//    }

    private List<User> common(){
        List<User> users = new ArrayList<>();
        for(int i = 35 ; i<76 ; i++){
            User user = testMapper.selectC(String.valueOf(i));
            if (user!=null){
                users.add(user);
            }
        }
        return users;
    }

    public void symbleOptional(){
        long startTime=System.nanoTime();
        System.out.println("执行代码块/方法");
        System.out.println(common());
        long endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
    }



    Callable ca1 = new Callable(){
        @Override
        public User call() throws Exception {
            User user = testMapper.selectC("35");
            try {//模拟任务，执行了500毫秒；
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return user;
        }
    };
    @Test
    public void sk2(){
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ){
            String a上= "2";
            br.readLine();
           String a = "2";
        }catch (Exception e){
            e.printStackTrace();
        }
    }







//
//    @ResponseBody
//    @RequestMapping("/symbleOptional")
//    public void optional() {
//        List<User> users = new ArrayList<>();
//
//
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        User user = null;
//                        for (int i = 35;i<40;i++) {
//                            final String ss = String.valueOf(i);
//                            user = userService.userFindById(ss);
//                            System.out.println(user.toString());
//                            System.out.println(Thread.currentThread().getName());
//                        }
//                        users.add(user);
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            }).start();
//
//        }

    @ResponseBody
    @RequestMapping("/symbleOptional")
    public void optional() {
        long startTime=System.nanoTime();
        final List list = new ArrayList();
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 75; i++) {
            final String ss = String.valueOf(i);
            Future<User> future = service.submit(new Callable() {
                @Override
                public User call() throws Exception {

                    User user = userService.userFindById(ss);
//                    System.out.println(Thread.currentThread().getName());

                    return user;
                }
            });
            try {
                User result = future.get();
                list.add(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime));
        System.out.println(list);
    }


    @ResponseBody
    @RequestMapping("/symbleOptional2")
    public void ssfdfa()throws Exception{
        long startTime=System.nanoTime();
        List list = new ArrayList();
        for (int i = 1; i < 75; i++) {
            User user = userService.userFindById(String.valueOf(i));
            list.add(user);
        }
        long endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime));
        System.out.println(list);
    }

//    @RequestMapping("/symbleOptional")
    public void asdasdass() throws Exception{
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add(null);
        list.add("444");
        list.add("555");
        list.stream().forEach(this::lists);
    }

    public void lists(String ss){
        try {
            System.out.println(            ss.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping("/thread")
    @ResponseBody
    public JSONObject timer(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sta","Success");
        logger.info("进入timer",jsonObject);
        try {
            Thread.sleep(6000);
        }catch (Exception e){
            logger.error("超时",e);
        }
        return jsonObject;
    }


    @Test
    public void sSwW(){
        BigDecimal bigDecimal = new BigDecimal(10);
        System.out.println(bigDecimal.divide(new BigDecimal(0)));
    }





}


