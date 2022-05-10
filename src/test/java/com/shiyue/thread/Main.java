package com.shiyue.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shiyue.springboot.domain.CertificatePrivacy;
import com.shiyue.springboot.domain.School;
import com.shiyue.springboot.domain.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Logger
public class Main {
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        //创建一个Callable，3秒后返回String类型
//            Callable myCallable = new Callable() {
//                @Override
//                public String call() throws Exception {
//                Thread.sleep(3000);
//                System.out.println("calld方法执行了");
//                return "call方法返回值";
//                }
//            };
//            System.out.println("提交任务之前 "+getStringDate());
//            Future future = executor.submit(myCallable);
//            System.out.println("提交任务之后，获取结果之前 "+getStringDate());
//            System.out.println("获取返回值: "+future.get());
//            System.out.println("获取到结果之后 "+getStringDate());
//        }
//    public static String getStringDate() {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        String dateString = formatter.format(currentTime);
//        return dateString;
//    }



    @Test
    public void thread(){
        List<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap();
        map1.put("app","12345");
        HashMap<String,Object> map2 = new HashMap();
        map2.put("app","123456");
        HashMap<String,Object> map3 = new HashMap();
        map3.put("app",null);
        HashMap<String,Object> map4 = new HashMap();
        map4.put("app","123458");
        HashMap<String,Object> map5 = new HashMap();
        map5.put("app","123459");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
//        List<HashMap<String,Object>> list1 =list.stream().filter(map -> map.get("app")!=null).collect(Collectors.toCollection(ArrayList::new));
//        List<HashMap<String,Object>> list1 =list
//                .stream()
//                .filter(map -> map.get("app")!=null)
//                .map(map -> { map.put("add","add");return map; })
//                .collect(Collectors.toCollection(ArrayList::new));
        try {
            xuexi(list);
        }catch (Exception e){
//            log.error("外层捕获",e);
        }
//        System.out.println(list1);
    }

    public void xuexi(List<HashMap<String,Object>> list ){
        list.stream().forEach(consumerWrapper(map -> System.out.println(map.get("app").toString()),Exception.class));
    }


    @FunctionalInterface
    public interface ThrowingConsumer<T, E extends Exception> {
        void accep(T t) throws E;
    }
        public static <T> Consumer<T> throwing(ThrowingConsumer<T, Exception> throwingConsumer) {
            return i -> {
                try {
                    throwingConsumer.accep(i);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            };
        }

    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println(
                        "Arithmetic Exception occured : " + e.getMessage());
            }
        };

    }

    private static  Consumer<String> exceptionClass(Consumer<String> consumer, Class<Exception> clazz) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                try {
                    Exception exCast = clazz.cast(ex);
                    System.err.println("userid为"+i+"发送短信出错"+exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw ex;
                }
            }
        };
    }

    static <T, E extends Exception> Consumer<T>
    consumerWrapper(Consumer<T> consumer, Class<E> clazz) {

        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = clazz.cast(ex);
                    System.err.println(
                            "Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw ex;
                }
            }
        };
    }


    @Test
    public void ssss(){
        User user = new User();
        User user12 = user;
        User user1 = new User();
        System.out.println(user==user12);
        System.out.println(user.equals(user1));
    }

    @Test
    public  void concurrentFun() {
        List<Integer> listOfIntegers = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            listOfIntegers.add(i);
        }
        List<Integer> parallelStorage = new ArrayList<>() ;
        listOfIntegers
                .parallelStream()
                .filter(i->i%2==0)
                .forEach(i->parallelStorage.add(i));
        System.out.println();

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));

        System.out.println();
        System.out.println("Sleep 5 sec");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
    }



    @Test
    public void sdu(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        for (String str:list){
            if (str.equals("555")){
                list.add("666");
            }
        }
        System.out.println(list);
    }



    @Test
    public void exception(){
        try {
            List<String> list = new ArrayList<>();
            list.add("111");
            list.add("222");
            list.add("333");
            list.add(null);
            list.add("444");
            list.add("555");
            for (String str:list) {
                try {
                    System.out.println(str.toString());
                } catch (Exception e) {
                    System.out.println("内层");
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println("外层");
            e.printStackTrace();
        }
    }

    @Test
    public void asdasdass(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add(null);
        list.add("444");
        list.add("555");
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        for (String string:list){
            try {
                System.out.println(string);
                string.toString();
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("11111=============");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("11111------------");
            }
        }
    }

    public void lists(String ss){
        try {
            System.out.println(ss);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void ssssss(){
        BigDecimal bigDecimal = new BigDecimal(0.0000000000000000);
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO)==0);


    }


    @Test
    public void cd(){
        String[] listFiles = {"credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt",
                "credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt",
                "credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt",
                null,
                "credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt.ok",
                "credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt",
                "credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt.ok",
                "credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt",
                "credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt.ok"};

//        String[] listFiles = {};
//        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
//        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
//        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt");
//        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt.ok");
//        list.add("credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt");
//        list.add("credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt.ok");
//        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt");
//        list.add("credit_report_9064_20200619.002.xlsx.result.20200617193558856.txt.ok");
        List<Map<String,Object>> listMap = new ArrayList<>();
        Map map = new HashMap();
        map.put("num","credit_report_9064_20200617.001.xlsx");
        Map map2 = new HashMap();
        map2.put("num","credit_report_9064_20200618.001.xlsx");
        Map map3 = new HashMap();
        map3.put("num","credit_report_9064_20200619.002.xlsx");
        Map map4 = new HashMap();
        map4.put("num","credit_report_9064_20200621.001.xlsx");
        Map map5 = new HashMap();
        map5.put("num","credit_report_9064_20200622.001.xlsx");
        listMap.add(map5);
        listMap.add(map);
        listMap.add(map2);
        listMap.add(map3);
        listMap.add(map4);
        listMap.stream()
                .map(map1 -> map1.get("num").toString())
                .forEach(s-> Arrays.stream(Optional.ofNullable(listFiles).orElse(new String[0]))
                        .collect(Collectors.toCollection(ArrayList::new))
                        .stream()
                        .filter(s1 -> s1.length()>36&&s1.endsWith(".ok")&&s1.substring(0,36).equals(s))
                        .map(s1 -> s1.substring(0,s1.length()-3))
                        .filter(s1 -> s1.endsWith(".txt"))
                        .forEach(s1->System.out.println(s1)));
    }

    public void ssssd(){
        FTPFile[] ftpFiles = new FTPFile[]{};
    }


    @Test
    public void xmsl(){

        String str = "abc";

        boolean status = str.contains("a");

        if(status){
            System.out.println("包含");

        }else{
            System.out.println("不包含");
        }

    }


    @Test
    public void xml(){
        String json = "{\"errorMessage\":\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\\r\\n<Document><errNum>6</errNum><errorInfo><errRecId>8</errRecId><businessNumber>HTHCCX2020042815430083516</businessNumber><businessDate>2020-05-28</businessDate><errMsg>ERRORCODE_0317:合同开立信息已报送，不能重复上报！</errMsg></errorInfo><errorInfo><errRecId>10</errRecId><businessNumber>HTHCCX2020050618425998115</businessNumber><businessDate>2020-07-30</businessDate><errMsg>ERRORCODE_0317:合同开立信息已报送，不能重复上报！</errMsg></errorInfo><errorInfo><errRecId>11</errRecId><businessNumber>HTHCCX2020050618435914014</businessNumber><businessDate>2020-07-30</businessDate><errMsg>ERRORCODE_0317:合同开立信息已报送，不能重复上报！</errMsg></errorInfo><errorInfo><errRecId>12</errRecId><businessNumber>HTHCCX2020050620425766570</businessNumber><businessDate>2020-07-30</businessDate><errMsg>ERRORCODE_0010:系统错误！根据报数类型校验报数请求出错！</errMsg></errorInfo><errorInfo><errRecId>13</errRecId><businessNumber>HTHCCX2020051210594875503</businessNumber><businessDate>2020-11-03</businessDate><errMsg>ERRORCODE_0315:assureBeginDate担保起始日期应小于等于当前系统时间！ERRORCODE_0714:realInBulgaria实际在保责任信息段的balanceChangeDate余额变化日期应小于等于当前系统时间！</errMsg></errorInfo><errorInfo><errRecId>14</errRecId><businessNumber>HTHCCX2020062814535777601</businessNumber><businessDate>2020-11-05</businessDate><errMsg>ERRORCODE_0315:assureBeginDate担保起始日期应小于等于当前系统时间！ERRORCODE_0714:realInBulgaria实际在保责任信息段的balanceChangeDate余额变化日期应小于等于当前系统时间！</errMsg></errorInfo></Document>\\r\\n\",\"preCheckErr\":[],\"sendInfo\":\"数据已发送\",\"sendSuccess\":\"1\"}";
        HashMap hashMap = JSON.parseObject(json.toString(),HashMap.class);
        System.out.println(hashMap);
        System.out.println(hashMap.get("preCheckErr"));
        List<Map> hashList = JSON.parseObject(hashMap.get("preCheckErr").toString(),List.class);
        System.out.println(hashList.get(0).get("errInfo"));

    }

    @Test
    public void ssssssssc(){
        String str = "ERRORCODE_0305:assureFinishDate担保到期日期应大于等于assureBeginDate校验担保起始日期！ERRORCODE_0315:assureBeginDate担保起始日期应小于等于当前系统时间！ERRORCODE_0714:realInBulgaria实际在保责任信息段的balanceChangeDate余额变化日期应小于等于当前系统时间！";
        String strr = "ERRORCODE_0305:assureFinishDate担保到期日期应大于等于assureBeginDate校验担保起始日期！";
        String str1=str.substring(0, str.indexOf("！"));
        System.out.println(str1);
        System.out.println(str.length());
        System.out.println((str.length()>100?str.substring(1,100):str));
        System.out.println((strr.length()>100?strr.substring(1,100):strr));

    }

    @Test
    public void shshsh(){
        String s = "78";
        int a = 1;
        if ((!"".equals(s))&&a==1){
            System.out.println("11");
        }
    }


    @Test
    public void asdasdas(){
        String s = null;
        try {
            s.toString();
            System.out.println("正常1");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常1");
        }
        try {
            System.out.println("正常2");
        }catch (Exception e){
            System.out.println("异常2");
        }finally {
            System.out.println("关闭");
        }
    }


    @Test
    public void sdsa(){
        String sss = "credit_report_9060_20200523.123.xlsx.ok";
        System.out.println(sss.length());
        String rrr = "credit_report_9064_20200617.028.xlsx.result.20200617181200713.txt.ok";
        System.out.println(rrr.length());
        String sssssss = "12345asdasd上山打老虎dasdasd";
        System.out.println(sssssss.substring(0,20));
        String sfs = "ERRORCODE_0315:assureBeginDate担保起始日期应小于等于当前系统时间！";
        System.out.println(sfs.length());
    }


    @Test
    public void sadasfas(){
        String ss = "ERRORCODE_0708:在上报实际在保责任发生变化，balanceChangeDate余额变化日期应大于上一次所录入的 余额变化日期";
        System.out.println(ss.length());
        System.out.println(ss.length()>50?ss.substring(0,50):ss);
        Map<String,String> map = new HashMap();
        map.put("ss",null);
        String dd = map.get("ss");
        dd.toString();
    }

    @Test
    public void sssss(){
        List<String> list = new ArrayList<>();
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200617.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt");
        list.add("credit_report_9064_20200618.001.xlsx.result.20200617193558856.txt.ok");
        list.add("credit_report_9064_20200619.001.xlsx.result.20200617193558856.txt");
        list.add("2");
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

    private void sssfff(String ccccc) throws Exception{
            System.out.println(ccccc.substring(5));
    }

    @Test
    public void sk2(){
        Integer c = -128;
        Integer d = -128;
        System.out.println("c == d: " + (c == d));
        System.out.println("c.equals(d): " + c.equals(d));
        System.out.println("c.intValue() == d.intValue(): " + (c.intValue() == d.intValue()));
        System.out.println("Objects.equals(c, d): " + Objects.equals(c, d));

        Integer e = 127;
        Integer f = 127;
        System.out.println("e == f: " + (e == f));
        System.out.println("e.equals(f): " + e.equals(f));
        System.out.println("e.intValue() == f.intValue(): " + (e.intValue() == f.intValue()));
        System.out.println("Objects.equals(e, f): " + Objects.equals(e, f));

        Integer g = 128;
        Integer h = 128;
        System.out.println("g == h: " + (g == h));
        System.out.println("g.equals(h): " + g.equals(h));
        System.out.println("g.intValue() == h.intValue():" + (g.intValue() == h.intValue()));
        System.out.println("Objects.equals(g, h): " + Objects.equals(g, h));
    }

    @Test
    public void abs(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("333");
        list.add(null);
        list.add("444");
        list.add("555");

//        list.stream().forEach(s -> Es(s));

        }
//    @Test
//    public void run(){
//        try {
//            abss();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }




//    public void abss(){
//        List<String> list = new ArrayList<>();
//        list.add("111");
//        list.add("333");
//        list.add(null);
//        list.add("444");
//        list.add("555");
//        try {
//            for (String st : list){
//                Es(st);
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    public static void main(String[] args) {

        try {
            List<String> list = new ArrayList<>();
            list.add("111");
            list.add("333");
            list.add(null);
            list.add("444");
            list.add("555");
            final String s = "sss";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                        s2222(s);
                    }
                }).start();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally111111111111111");
        }
    }

    public static void s2222(String s){
        try {

            System.out.println(s);
            s.toString();
            System.out.println(222222222);
        } catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("finally22222222");
        }
    }


    @Test
    public void  ssssssss(){
        try {
            Map map = new HashMap();
            System.out.println("0".equals(map.get("ac")));
//        String[] str = {"2","3",null,"4"};
            String[] str = null;
//            Arrays.stream(Optional.ofNullable(str).orElse(new String[0])).forEach(System.out::println);
            Arrays.stream(str).forEach(System.out::println);

        }catch (Exception e){
            System.err.println(e);
        }
    }



    public List svv(String key){
        Map<String,List> map = new HashMap();
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        map.put("1",list);
        map.put("2",list);
        map.put("3",list);
        map.put("4",list);
        map.put("5",list);
        return map.get(key);
    }

    @Test
    public void sssssds(){
        List<String> list = null;
        for (int i= 0;i<6;i++){
            list = svv(String.valueOf(i));
            if (list!=null){
                System.out.println(list.hashCode());

            }
        }
    }


    @Test
    public void asdasfa(){
        String a = "{'responseMsg':'担保合同已存在'}";
        System.out.println(a.substring(a.length()-4));
        com.alibaba.fastjson.JSONObject res = com.alibaba.fastjson.JSONObject.parseObject(a);
        System.out.println("担保合同已存在".equals(res.get("responseMsg")));


    }


    @Test
    public void asdasd(){
        String s = null;
        s.toString();
        try {
            System.out.println("try");
        }catch (Exception e){
            System.out.println("catch"+e);
        }finally {
            System.out.println("finally");
        }
    }

    @Test
    public void sdasdasdasda(){
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(null,"sss");
        linkedHashMap.put(null,"aaa");
        System.out.println(linkedHashMap.get(null));
        System.out.println(linkedHashMap.get("ccc"));

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedList linkedList = new LinkedList();

        Hashtable balance = new Hashtable();
        balance.get("ss");

    }

    @Test
    public void sadsfafa(){
        String s = "ERRORCODE_0317:assureBeginDate担保起始日期应小于等于当前系统时间！ERRORCODE_0714:realInBulgaria实际在保责任信息段的balanceChangeDate余额变化日期应小于等于当前系统时间！";
        System.out.println(s.contains("ERRORCODE_0317"));
        if (s!=null&&!s.equals("")){
            System.out.println("2222");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1111");
    }



    @Test
    public void asdfasfa(){
        Arrays.stream(new FTPFile[0]).map(FTPFile::getName).forEach(System.out::println);
        System.out.println(new FTPFile[0].length);
    }

    public List sks(){
        List<Map<String,Object>> reMap = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("num","1234");
        map.put("amount","33.66");
        reMap.add(map);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("num","1234");
        map2.put("amount","332.66");
        reMap.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("num","1235");
        map3.put("amount","33.66");
        reMap.add(map3);
        Map<String,Object> map4 = new HashMap<>();
        map4.put("num","1235");
        map4.put("amount","3.66");
        reMap.add(map4);
        Map<String,Object> map5 = new HashMap<>();
        map5.put("num","1235");
        map5.put("amount","33.66");
        reMap.add(map5);
        Map<String,Object> map6 = new HashMap<>();
        map6.put("num","1235");
        map6.put("amount","3.66");
        reMap.add(map6);

        return reMap;
    }

    public List sks1(){
        List<Map<String,Object>> reMap = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("num","12345");
        map.put("amount","333.66");
        reMap.add(map);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("num","12345");
        map2.put("amount","332.66");
        reMap.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("num","12356");
        map3.put("amount","333.66");
        reMap.add(map3);
        Map<String,Object> map4 = new HashMap<>();
        map4.put("num","12356");
        map4.put("amount","33.66");
        reMap.add(map4);
        Map<String,Object> map5 = new HashMap<>();
        map5.put("num","12356");
        map5.put("amount","333.66");
        reMap.add(map5);
        Map<String,Object> map6 = new HashMap<>();
        map6.put("num","12356");
        map6.put("amount","33.66");
        reMap.add(map6);

        return reMap;
    }
    @Test
    public void sdasd(){
        List<Map<String,Object>> listr = sks();
        long start = System.currentTimeMillis();
        Supplier<Map<String,Object>> mapFct = HashMap::new;
        List<Map<String,Object>> cpList = new ArrayList<>();
        listr.stream()
                .collect(Collectors.groupingBy(map1 -> map1.get("num")))
                .forEach((k,list)->{
                    Map<String,Object> nmap= mapFct.get();
                    nmap.put("num",k);
                    BigDecimal bigDecimal = list.stream()
                            .map(m->m.get("amount"))
                            .map(mapcccd -> new BigDecimal(String.valueOf(mapcccd)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    nmap.put("amount",bigDecimal);
                    cpList.add(nmap);
                });
        System.out.println(cpList);
        long end = System.currentTimeMillis();
        System.out.println("耗时（毫秒）：" + (end - start));
    }

    @Test
    public void scjshf(){
        List<Map<String,Object>> listr = sks();
        System.out.println(listr);
        Map mapcd = listr.stream().collect(Collectors.groupingBy(map1 -> map1.get("num")));
        mapcd.forEach((k,list)->{
            System.out.println(list);
        });
    }


    public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }

    @Test
    public void shsdaaa(){
        List<Map<String,Object>> listr2 = sks();
        long start = System.currentTimeMillis();
        List list = listr2.stream()
                .collect(Collectors.groupingBy(map1 -> map1.get("num")))
                .entrySet()
                    .stream()
                    .map(set-> set.getValue()
                            .stream()
                            .map(map->map.get("amount"))
                            .map(amount -> new BigDecimal(String.valueOf(amount)))
                            .reduce(BigDecimal::add)
                            .map(bigDecimal -> new HashMap(){{put(set.getKey(),bigDecimal);}}))
                    .map(Optional::get)
                    .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(list);
        System.out.println("耗时（毫秒）：" + (end - start));
    }


    public void streamS(){
        List<Map<String,Object>> listr2 = sks();
        List list = listr2.stream()
                .collect(Collectors.groupingBy(map1 -> map1.get("num")))
                .entrySet()
                .stream()
                .map(set-> set.getValue()
                        .stream()
                        .map(map->map.get("amount"))
                        .map(amount -> new BigDecimal(String.valueOf(amount)))
                        .reduce(BigDecimal::add)
                        .map(bigDecimal -> new HashMap(){{put(set.getKey(),bigDecimal);}}))
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(list);
    }




    @Test
    public void cdcdc(){

        for (int i = 6;i<10;i++){
            if (i ==8){
                System.out.println("上");
                continue;
            }
            System.out.println(i);
        }

    }

    @Test
    public void sttr(){
        String s = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKZ8SHj47NBvrUgpaPsJdwcJ9QpgW2zZk0XVeJmg/gVnfLa3YQbkYs/CQLH2YvJR4nuw12Mkn8qp6JvVitkyMy/SRwWW0OlhhKBDeAD1KzkkL5Mfay/6wJ1RNqKfUIwW+YLRbxFid70i79hthGFfygLBDw6KFHmW+kLcKtG3obkfAgMBAAECgYAJ1g3yF8LmDbMPMGMjccu2fsAbfNXp+HDbD2GwsfJQH20e3na9EYFFBRUNbqEp7+yRokEXenJ2ZtN3YYZyjqS3dbPhe1WlaFyd5kwRjZnR6S4A7+tsIUA38n5kHVlTSHPZkdF5EuaL1B5fk397iEMQXfAJxL81cQZNSth8QvZ+AQJBAPvodSbFBlZq0Wk3spTixSYcPzBoqx+lDCreHYzaDyeip4bVuxAkjRbABc5/wctq90SlmacJUxPIhYSezXxRnscCQQCpMJoCndI/3AnmSU7LbpFp+2Lhf5tKbGnUeOBUOAh0KC1t1fjxBxs+SgZVB9xsRAcwOEky2MIATVrizSInCBrpAkEAqPyb+lIZmkc2C9ttVSt7DcHNMfBFO4lAzFEMibyOhgw7Tdx7DK04aJaQTqKtooQzND3TAZbjwFW4G+tb9ZXbaQJAeminhO0aiQlyQCMZ1yWQH9W5nNzuRdJaX1d1Nnmz5BaNh8If8oLpuw9jtXGSkOJHMSQIEbmx4vYUo7M22naKwQJBAJfXFCCDkdwoQTaRUIQF41I8AZtnsCBh5NEKpbNxDCMvqZYuIM01bjUzviiX3S/zfEXHfhS/EwvvKrST70vghhw=";
        String a = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKZ8SHj47NBvrUgpaPsJdwcJ9Qpg" +
                "W2zZk0XVeJmg/gVnfLa3YQbkYs/CQLH2YvJR4nuw12Mkn8qp6JvVitkyMy/SRwWW0OlhhKBDeAD1" +
                "KzkkL5Mfay/6wJ1RNqKfUIwW+YLRbxFid70i79hthGFfygLBDw6KFHmW+kLcKtG3obkfAgMBAAEC" +
                "gYAJ1g3yF8LmDbMPMGMjccu2fsAbfNXp+HDbD2GwsfJQH20e3na9EYFFBRUNbqEp7+yRokEXenJ2" +
                "ZtN3YYZyjqS3dbPhe1WlaFyd5kwRjZnR6S4A7+tsIUA38n5kHVlTSHPZkdF5EuaL1B5fk397iEMQ" +
                "XfAJxL81cQZNSth8QvZ+AQJBAPvodSbFBlZq0Wk3spTixSYcPzBoqx+lDCreHYzaDyeip4bVuxAk" +
                "jRbABc5/wctq90SlmacJUxPIhYSezXxRnscCQQCpMJoCndI/3AnmSU7LbpFp+2Lhf5tKbGnUeOBU" +
                "OAh0KC1t1fjxBxs+SgZVB9xsRAcwOEky2MIATVrizSInCBrpAkEAqPyb+lIZmkc2C9ttVSt7DcHN" +
                "MfBFO4lAzFEMibyOhgw7Tdx7DK04aJaQTqKtooQzND3TAZbjwFW4G+tb9ZXbaQJAeminhO0aiQly" +
                "QCMZ1yWQH9W5nNzuRdJaX1d1Nnmz5BaNh8If8oLpuw9jtXGSkOJHMSQIEbmx4vYUo7M22naKwQJB" +
                "AJfXFCCDkdwoQTaRUIQF41I8AZtnsCBh5NEKpbNxDCMvqZYuIM01bjUzviiX3S/zfEXHfhS/Ewvv" +
                "KrST70vghhw=";
        System.out.println(a.equals(s));
        System.out.println(a.length());
        System.out.println(s.length());
    }

    @Test
    public void eq(User userin){
        User user = new User();
        User user1 = new User();
        for (School userc:userin.getSchools()){

        }

            System.out.println(user.equals(user1));
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
    }


    @Test
    public void two(){
        int i = 20;
        while (i>=0){
            if (i%2!=0&&i%3!=0&&i%5!=0){
                System.out.println(i);
            }
            i--;
        }
    }


    @Test
    public void math(){
        System.out.println(Math.round(-1.5));
        System.out.println(140%16);
    }

    @Test
    public void ss(){
        Map map1 = new HashMap();
        map1.put(1,"aa");
        map1.put(1,"bb");
        System.out.println(map1.get(1));
        Map map11 = new Hashtable();
        map11.put(null,"1");
    }


    @Test
    public void threadC(){
        try {
            Map map = new HashMap();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put("111","aaa");
                    map.put("222","bbb");
                    map.put("333","ccc");
                    map.put("444","ddd");
                    map.put("555","eee");
                    map.put("666","fff");
                    map.put("777","ggg");
                    map.put("888","hhh");
                    System.out.println("线程1"+map);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put("11","aaa");
                    map.put("22","bbb");
                    map.put("33","ccc");
                    map.put("44","ddd");
                    map.put("55","eee");
                    map.put("66","fff");
                    map.put("77","ggg");
                    map.put("88","hhh");
                    System.out.println("线程2"+map);
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void sadasfafa(){
        String a = null;
        System.out.println(a.substring(0,a.indexOf("|")));
        System.out.println(a.substring(a.indexOf("|")+1,a.length()));
    }

    @Test
    public void subIndex(){
        String a = "123124124|asfasdfadsgf";
        String b = "|aasfasffasf";
        String c = "124124124|";
        System.out.println(b.substring(0,b.indexOf("|")).equals(""));
    }

    @Test
    public void image(){
        String filePath = this.getClass().getClassLoader().getResource("templates/微信图片_20200619112025.jpg").getPath();
        System.out.println(filePath);
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);
    }

    @Test
    public void gsdfsd(){
        String s = "<pre style=word-wrap: break-word; white-space: pre-wrap;>{message:'SYS0000011',params:'',status:'success'}</pre>";
        System.out.println(s.substring(57,s.length()-6));
    }
    @Test
    public void listCC(){
        Long totalRows = 900L;
        Long pageSize = 500L;
        Long pages = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
        System.out.println(pages);
    }



    @Test
    public  void yinyong() {
        // 使用双冒号::来构造静态函数引用
        Function<String, Integer> fun = Integer::parseInt;
        Integer value = fun.apply("123");
        System.out.println(value);

        // 使用双冒号::来构造非静态函数引用
        String content = "Hello JDK8";
        Function<Integer, String> func = content::substring;
        String result = func.apply(1);
        System.out.println(result);

        // 构造函数引用
//        BiFunction<String, Integer, User> biFunction = User::new;
//        User user = biFunction.apply("mengday", 28);
//        System.out.println(user.toString());

        // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
        sayHello(String::toUpperCase, "hello");
    }

    // 方法有两个参数，一个是
    private static void sayHello(Function<String, String> func, String parameter){
        String result = func.apply(parameter);
        System.out.println(result);
    }


@Test
    public void sssdasdasd(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("1");
        user.setAge(133);
        User user1 = new User();
        user1.setUsername("0");
        user1.setAge(12);
        User user2 = new User();
        user2.setUsername("1,0");
        user2.setAge(134);
        User user3 = new User();
        user3.setUsername("0,1");
        user3.setAge(144);
        User user4 = new User();
        user4.setUsername("0,1");
        user4.setAge(155);
        User user5 = new User();
        user5.setUsername("1");
        user5.setAge(15555);
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
//        System.out.println(BeanMap.create(user));
        User us = users.stream().peek(u -> u.setAddress(u.getUsername())).findFirst().orElse(new User());

        User userwe = users.stream().filter(user6 -> user6.getUsername().contains("1"))
                .sorted(Comparator.comparing(User::getAge).reversed())
                .findFirst().orElse(new User());
//        System.out.println(userwe);



    }

    @Test
    public void sud(){
        List list = new ArrayList();
        Object c = list.stream().findFirst();
        System.out.println(c);
    }

    @Test
    public void asddasd(){
        double a = 7;
        double b = 4;
        String aa = null;
        String bb = "null";
        System.out.println(bb.equals(aa));

        System.out.println(a-b);
    }


    public int getLoanCountByIdCard(String IdCardNo,String date){
        return 1;
    }

    public int getOverdueLoanByIdCard(String IdCardNo){
        return 1;
    }


    @Test
    public void suxe(){
        BigDecimal bigDecimal = null;
        BigDecimal bigDecimal2 = Optional.ofNullable(bigDecimal).orElse(new BigDecimal("0.00"));
        System.out.println(bigDecimal2);
    }


    @Test
    public void es() throws Exception{
        File file = new File("D:/otherRightsTempLet.xlsx");
        XSSFWorkbook wb = null;
        wb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet xssfSheet = wb.getSheetAt(0);
//        System.out.println(getStringValueFromCell(xssfSheet.getRow(1)));
//        InputStream inputStream =
    }

    @Test
    public void defaultJurisdiction(){
        CertificatePrivacy certificatePrivacy = new CertificatePrivacy();
        certificatePrivacy.setContractCutOffDateEnum(1);
        certificatePrivacy.setOwnershipSubjectEnum(1);
        certificatePrivacy.setOwnershipTypeEnum(1);
        certificatePrivacy.setRegisterDateEnum(1);
        certificatePrivacy.setProductDateEnum(1);
        certificatePrivacy.setProveTypeEnum(1);
        certificatePrivacy.setEngineNumEnum(1);
        certificatePrivacy.setIdCardNumEnum(1);
        certificatePrivacy.setCarBrandEnum(1);
        certificatePrivacy.setCarModelEnum(1);
        certificatePrivacy.setCarNumEnum(1);
        certificatePrivacy.setCarVinEnum(1);
        System.out.println(certificatePrivacy.toString());
    }


    public static String getStringValueFromCell(XSSFCell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String cellValue = "";
        if(cell == null) {
            return cellValue;
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = cell.getStringCellValue();
        }

        else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if(HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            }
            else {
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = "";
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            cellValue = "";
        }
        else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = cell.getCellFormula().toString();
        }
        return cellValue;


    }

    @Test
    public void skd(){
        try {
            String a = null;
            System.out.println(1);
            a.toString();
        }catch (Exception e){
            System.out.println(2);
        }
        System.out.println(3);
    }


    @Test
    public void sssddd(){
        System.out.println(3*1024*1024);
    }



    @Test
    public void asdasdasdasd(){
        for (int i = 0;i<5;i++){
            try {

                if (i==3){
                    continue;
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("后"+i);
        }

    }


    @Test
    public void isValidDate() {
        String str = "2020-02-34";
            boolean convertSuccess=true;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                format.setLenient(false);
                format.parse(str);
            } catch (ParseException e) {
                convertSuccess=false;
            }
        System.out.println(convertSuccess);
    }




    @Test
    public void asdasfdaw(){
        User user = null;
        String a = "";
        HashSet hashSet = new HashSet();

//        System.out.println(new BigDecimal(a));
//        System.out.println(user.getUsername());
        String as = "13611111116";
        System.out.println(Integer.valueOf(as));
    }


    @Test
    public void main() {
        //
        User p1 = new User();
        p1.setUsername("su");
        p1.setAge(22);
        User p2 = new User();
        p1.setUsername("sss");
        p1.setAge(23);
        Map hMap5 = new HashMap<>();
        hMap5.put(p1, "1111");
        hMap5.put(p2, "2222");
        System.out.println(hMap5 +"--"+ hMap5.size());  //2

        p1.setAge(5);
        System.out.println(hMap5);

        hMap5.put(p1, "333");
        System.out.println(hMap5);
        System.out.println(hMap5.get(p1));

    }

    @Test
    public void sadwadfwf(){
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        String a = "2020-11-22";

        try {
            System.out.println(sFormat.parse(sFormat.format(new Date())));
            Date date = sFormat.parse(sFormat.format(new Date()));
            Date date1 = sFormat.parse(a);
            System.out.println(date);
            System.out.println(date1);
            System.out.println(date1.after(date));
            System.out.println(date1.before(date));
        }catch (Exception e){

        }

        String b = "2020-12-22";
        System.out.println(a.compareTo(b));
    }

    @Test
    public void sadwadfwfs(){

        StringBuffer stringBuffer = new StringBuffer(",是大街上的123123123123");
        StringBuffer stringBuffer1 = new StringBuffer("是大街上的,");
        System.out.println(stringBuffer.deleteCharAt(0));
        HashMap hashMap = new HashMap();
        hashMap.put("a",1);
        UUID uuid = UUID.randomUUID();
        String requestId = uuid.toString().replace("-", "");
        System.out.println(requestId);

    }







    public void toBreak(){
        String s = null;
        new Thread("Thread"){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000);
                    System.out.println(s.toString());
                 }catch (Exception e){
                    try {
                        toBreak();
                    }catch (Exception ex){
                        toBreak();
                    }
                }
            }
        }.start();



    }


    @Test
    public void main2() {

        int[] num = {60,80,19,23,56,108};

        // 1. 外层for循环：比较的轮数
        for(int i=0;i<num.length-1;i++){
            // 2. 内存for循环：每轮比较的次数
            for(int j=0;j<num.length-i-1;j++){
                // 如果numj>numj+1的，调换位置
                if(num[j]<num[j+1]){
                    int temp = num[j];
                    num[j]=num[j+1];
                    num[j+1]=temp;
                }

            }
        }

        // 遍历数组，输出

    }




    @Test
    public void readText() {
        try {
            File file = new File("D:/INOUTDATA20201202.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GB2312");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            System.out.println(line);
            String[] firstLine = line.split("\\|"); // 读第一行
            System.out.println(firstLine);
        }catch(Exception e){
            System.out.println();
            }


        }

    @Test
    public void sadwa(){
        int a = 0;
        for (int i = 0;i<5;i++){
            int b = 1;
            a = a+b;
        }
        System.out.println(a);
    }


    @Test
    public void skp2(){
        System.out.printf("33333");
    }


    @Test
    public void replace(){
        String url = "&123&456";
        String sw = new String("&123&456");
        System.out.println(url.equals(sw));
        String url_change = url.replaceAll("&", "&amp;");
        System.out.println(url_change);
    }

    @Test
    public void replace1(){
        int a = 127;
        Integer b = 128;
        Integer c = 127;
        Integer d = Integer.valueOf(128);
        Integer d2 = Integer.valueOf(128);
        Integer f = Integer.valueOf(127);
        Integer f2 = Integer.valueOf(127);
        Integer e = Integer.parseInt("127");
        System.out.println(a==d);
        System.out.println(b==d);
        System.out.println(d2.hashCode());
        System.out.println(d.hashCode());
        System.out.println(f.hashCode());
        System.out.println(f2.hashCode());
    }

    @Test
    public void sidwaa(){
        String token = "DoubleEnter_O1469202111241755482592";
        String key = token.substring(0, token.indexOf("O"));
        System.out.println(key);
        System.out.println(token.substring(key.length()));
        System.out.println(token.substring(0,3));
        List<String> list = null;
        System.out.println(list.size());
    }



    @Test
    public void testRemove5() {
        List<User> u1 = new ArrayList<>();
        User user1 = new User();
        user1.setAddress("beijing");
        user1.setAge(18);
        User user2 = new User();
        user2.setAddress("beijing2");
        user2.setAge(19);
        User user3 = new User();
        user3.setAddress("beijing3");
        user3.setAge(17);
        User user4 = new User();
        user4.setAddress("beijing33");
        user4.setAge(17);
        u1.add(user1);
        u1.add(user2);
        u1.add(user3);

        List<User> u2 = new ArrayList<>();
        User user11 = new User();
        user11.setAddress("beijing");
        user11.setAge(18);
        user11.setUsername("2133");
        User user22 = new User();
        user22.setAddress("beijing2");
        user22.setAge(19);
        user22.setUsername("213");
        u1.add(user1);
        u1.add(user2);
        u1.add(user3);
        u2.add(user11);
        u2.add(user22);
        //Set<Player> playerSet = new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        Set<User> playerSet = new TreeSet<>(Comparator.comparing(User::getAge));
        playerSet.addAll(u2);
        playerSet.addAll(u1);

 /*new ArrayList<>(playerSet).forEach(player->{
 System.out.println(player.toString());
 });*/
        //将去重之后的结果打印出来
        new ArrayList<>(playerSet).forEach(System.out::println);
    }


    @org.junit.jupiter.api.Test
    public void base65(){
        String s= "0x89504E470D0A1A0A0000000D494844520000041A000000E50806000000FD5794800000187D4944415478DAEDDD09985D65790060B5A858696D1577ACB452C5BD2EAD756BE36EAA0454EEDC6566C218701290B0A7228B498194108AB14045148BD1685444C0884036424230154188202540582781811820249310E0F4FBD2333C4348C200C96496F77D9EEFF90FF7DEB919CEDC7CF9CE37E7FFFFE73C070000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006AFA2287658BA74E9398B162D7A74CE9C39C5AC59B3441FC7ECD9B38BF9F3E7DF17D1E21309C8CFF23300C0809645EC82050B8ACECECE62DDBA75623BC5BDF7DE5B5C76D9650F4451BBB74F25203FCBCF00000356FEA64C11DB6F8AD975B366CDBAC1A712909FE5670080012B6FC75544F69F884276BD4F25203FCBCF00000356CE415540F6AB42B6F0A904E467F9190060D017B20FDDBFBC587AE5D9C5F5B38FDB10799C8F293E15B280FC2C3F0300F0B40AD9552B3B8AEB2E39B6B8F6C2714F887C2C9F53802A6401F9597E0600A0D785EC9DD7FFE249456C77DC75FD0C05A84216909FE56700007A5FC8DE70E9499B2D64F33905A84216909FE56700007A5DC85E376BC2660BD97C4E01AA9005E467F919000085AC4216909F85FC0C00D0F7856CAE62BEB942369F53802A6401F9597E0600A0D785EC4D0B4FDF6C219BCF294015B280FC2C3F0300D0EB427645C7E2E2BA99E39F7C5B6E3C96CF294015B280FC2C3F0300D0EB4236E3D6DF7EFF49856C3EA6F854C802F2B3FC0CC0905214C58E8B172F1E3D75EAD4CB264D9AF4C041071DB4BEADADADA8D56A454B4BCB63FBEFBFFFBAA38F3E7AE949279D342DE24DCE180AD94DC4DAB5C54D579CF1E4DB72E3B17C4E01AA9005E467F9198021D16038F7DC73BF7FFCF1C7AF3FE490438A33CF3CB358B46851D1D1D1517475751529C7CECECEE2AAABAE2AA64E9D5A1C7CF0C18F1D7AE8A1B7B5B7B7EFE70CA290FDFF58B5B2A358B2F0B4CDCE01CEE7F2358A50852C203FCBCF000C5A8B162D1A75C20927AC1D376EDC8626C2238F3C52F4D635D75C531C79E4918F8C1E3DFA864AA5B29BB3C9902D64D7AE2D96DF34B7F8FDC5476DB688ED8E7C4DBED66FCF14B280FC2C3F0330A81445B1C305175C3077CC9831C58C19339E56836163175F7C71316AD4A835EE6E602816B24FF55B32BF3D53C802F2B3FC0CC0906832CC9B37EF3763C78E2D962C59526C0DB7DD765B3166CC9835FBEDB7DF979D61865221DB9BDF926DE9B7670A52852C203FCBCF000C78792743361972CD85AD29DFAFBDBD7D55BD5E6F7196192A85EC332D62BB4341AA9005E467F91980012DD764C8E9125BEB4E864DDDD93072E4C895F57A7D77679BA150C80A852C203F0BF91960C8CADD258E3BEEB875B926C3B674E18517AE696E6EFEB5338E4256286401F9597E0660103BEFBCF37E98BB4B3CFCF0C3C5B676C00107DC6D0A050A59A19005E467F91980412AEF6638FEF8E3D7E716967DE1DA6BAF5DE7AE0614B242210BC8CFF2330083D4E2C58B47E70290CF661BCBA76BD4A85177D76AB55D9D7D14B242210BC8CFF2330083CCD4A9532F3BEBACB38ABE3465CA946B6AB5DA11CE3E0A59A19005E467F919804166D2A4490F2C5AB4A84F1B0D575C71C56DB55A6D86B38F4256286401F9597E06609039E8A083D62F5FBEBC4F1B0D77DF7D7767AD56BBD3D947212B14B280FC2C3F0330C8B4B5B5155D5D5D7DDA68C83FAF56AB7539FB2864854216909FE567000699B8E02FB687FC739D7D14B242210BC8CFF2330083CC3EFBECF3585FDFD1B07AF5EADC75229B0D6B22EE8BB8A35EAFFF6F8C57475C1E3133E2FC881F459C55AD564F8D71528C5FCB452423BE1CC75F8CB11A5FB7478C1F8BF1FDF1D83B1B8DC6DF562A95D7C6F897C3870F7FA19F300A59852C203F0BF919803E3476ECD8757DBD46C3B265CB6ECC351A2A95CA8B468E1CF9B2387E5D1CBF29C6773535357DB05EAF7FA25AADEE19633D1EDB3786B1317E25C67F8BF1E478EEBF623C3BE227B9A864C49C882B2272378B25117745FC31626DC42311AB22EE89B835DEE3FA18AF8CF1B2182F8A3837E2071167C6FB4E89F1DF238E89E3C3621C13E3C818F76E341AFF12E3B018FF211E7B5B8C7F13DFF3AB9A9B9BFF7CD8B0613BF8240D4EBDDD865521AB9005341A84FC0C40E9D8638FBDA9AF779DB8E4924B7ED557BB4E4C9830E179954A65A7D6D6D657E445631CBF25C6F7363535FD53BD5EFF74B55AFD7C8C2DF1587B8C87C478548C27C4F8F578EE5B713C358ECF89E30B63BC34E27F227E1F714BC4F2880722D6473C1C717FC4B2889BE3F58B635C145F3F37C65FE67B94EF75468CA7C4787C8C5F8DD71D1CC75F8AB139C6CF351A8D4FC5F8E118DF138FBD39C6D7C7F7FCF2F8FE5F9CFF2F3EB17DDE68B8347E4EB3B2B9A49055C8021A0D427E06A0174E3EF9E4B3429F361AC68F1F9F17ED470CA6F3D8DEDEFEFC4AA5F29296969657C745E91B9A9B9BDF1EFF8FEF6B6A6AFA488C9F89C72A71C1BA4F8CFBC778783C766CDE3D11C7DF88F1DB314E8BF1E7F1FCC531CE8FF86DC41F22722BD0DCA5E3A188477311CD8815794748C48DF1FADF957773CC8EE35FC4F8E378AFFF8EF1F41827C73821C67F8DE70E8CE35131C6501B11DFD7C763FC40A3D1F8BB78EC8DF1FDEE12DFFF4BDBDADA76F4B7E2098D861BCA693EEBE33C7D27CEE5CE0A59852CA0D120E46700B6E098638E79C381071EF8E8238F3CD2679B4EC4C5ED2DBDBD259D27CA46403604B231900D826C1464C3A06C1C8CC84642B97E45AE63312EC6F1319E14E36911DF8D0BE5E9315E90BFA58F7161D9A8B8B16C5CAC281B198F968D8D7B236E2F2FB6AF8A581071497CCD7931FE302FBC63FCCF78AF13B371920D94B291920D954A3658CA46CBFBB2F1920D986CC4644326E20503A4D1706BD968D810F1FFB032E2C8BC4B4621AB9005B69FFC772CE2431A0DF23300FDD461871D76F355575DD557D326F237F70B9CF57EEDB9395523A76CE4D48D720AC77B724A471C7F320ABBBDE2B811E37E311E9417DE311E17E37FC4F8CD88EFC5733FCD2923E5D49145E554929BCBA925F797534DD6C7F30FC6988B832E8DB82EE23711F3227E155FF3B318BF9F5358722A4B39A5E5A8728A4B4E756929A7BE7C3AA7C2E49498387E6B7CDF7F1DE32B478C18F16771FC275BB3D1D023B23133A67B7D0E85AC4216E8F346F0867C5C4E6FFB478D06F919807E66DF7DF7AD1C7EF8E1EBFBE0AE869571C17A755E203AEBE4457A2EA6998B6A96CD81B746B1F8F731FE738CC363FC421491AD318E8EF1D078ECE81827968B769E592EE2998B795E542EEE7965B9D8E7ADE5E29FABCAC540D7E59D08317644DC14716DC4AFCB454467948B8A9E5D2E327A72B9E8E857CA4548EFDD4CA3A1FB0E87DF3535357D4121AB9005B64FA3A1475CB4A93B1CE467F91980ED68F4E8D18B67CC98B14DBB0CB91E44DECD609706FA524ED3686B6BFB8BF8ECBD268E778B42F41DF9DBAF183F1AE367636C8AE7DA623C20D70E29B751CDED544F8DC71EDD52A3219E7F38A24B21AB9005B67BA3E1F13B1C7A361CE467F91980EDFB0FF6AEA3468D5AB364C9926DD26458BC78F105B94B43FCE3BFBBB3CD002F6473979173727D0C85AC4216E85F8D869E77386453597E969F01D8CE5A5A5A9A478F1EBDBAB3B373AB3619EEBAEBAECBE3A2EC76532618E085ECBDE5B48D5D377E9D42B67F452F2E4084104324E4678D0600FA81E6E6E651EDEDEDABB6D69D0D792743361972C140679701F6776197B250BD2917A0DCD2D69F0A59852CB05D1BC13DE3CA9C1667B15EF919807E26B7486C696979E0FCF3CF7FE8592C10B9F294534E393BA74BE4FC77679581A65EAFEF9C9FDDDEAC29D2DF0BD9F81637195BEB79852CD00F1A0D4F6830C8CFF23300FDF31FF05D1B8DC69CD1A347DF7DF5D557AF791A0D86AE9933674E6F6969B932177EB4260343C1402864B7F4D8B37D5E210B6CC746C3AF73DBE5CD3585E567F91980FEF90FF9DED970686B6BEB9C3469D2F5975F7EF9ED1D1D1DCBBBBABA367415D6AC5973F7B265CB6E9C3D7BF62F274C9870517373F3D26C30F4F637C1A0D1A09055C802CFA03ED9E41D0CF2B3FC0CC00092F3D5739E7AAEB61F716B4457F99B841CEF8C98915B036E6AA13CD06850C82A6401F97968E7E7B56BD716514F66ED7877C435B93348C4D9515F9E58AD560FCEEDA6C33FC5F11B478C18F1673EBD0080425621ABD100C8CFF2F31663E6CC9945A55279556E115DAD5687477CB15EAF7F35C65363FC69AD569B1FB1246255C4EA885B2216C6F33F8BF1F4886322F68DF84CBCC77BE2BD5EEB0E5A004021AB90D56800E467F9F929B5B6B6BEB85AADBEA15EAF7F28A7F2C6F181717C421C7F378E2F8CF1AA8865110F4774C6638B63BC245E33358E4F8AE3437381F3302CD70CAB542A2FF137020050C82A64351A00F9597EDEA20913263CAFB5B5F515F57AFD1D8D46E35331EE53ABD5BE12E33762FC71C4BC38FEDF181F28A7FCE614E05F47FC3CE29BD56AF56B317E295EB3471CFF7D1CBFAEBDBDFDF9FEF600000A59DBA7693400F2B3FCBC45954AE545117F5DABD53E50AD563F5FAFD70F88E3E3E2F83BE57A63B96BDA5D11EB22EE8BB82E5E332BC61F449C1CC787C7D888F1A3F13E6F8978A9BF6500C0902C64875A683400F2B3FCFC2C3DB75EAFEF5CAD56DF16E3276AB55A6B1C8F8BF1EB113F8AC7E6C6F887786C658C6B236E8FF89F88F3E3B16FC53821624C1CEF19E3FB1A8DC6EB870F1FFE427F23010085AC4216909F85FCBC45D940C846423614EAF5FA5ED960281B0D67465C5036206E2F1B127FCC0645D9A8F8618CA7640323C696A6A6A68F978D8D9DB3D1E16F2F00286415900A59407E16F2F316E5548B9C7251ABD53E56AD569BCBA9183925635A8CB373AA46C48A72EA466E1F7F65BCEE17317E3BA77894533D3E17E3FB730A484E05F1B71C0014B242210BC8CFF2334FD59078412E4A592E4E3922A2BD5CB4F28C18CF2B17B3BCB55CDCF2FE72B1CB5CF4727A3C3F2517C38C7164C4277391CC5C2C3317CD74660140212B14B280FC2C3FB3456D6D6D7F91DB773635357D24C67AB9AD676EEFF9FD8899E5B69FF796DB807694DB82FE32E2ACDC2EB4DC36F40BF1F51FCCED44F7D8638F3F75560140212B14B280FC2C3FB345C3860DDBA152A9BCB6D168BCA75AAD7EB65EAFEF57ABD58E89E3FF8AF1DC888511B744AC89E71E8C7149C4FC889F44FC673CF6D518DB62FC74BCC7DFC57BBDCA5D123C5BCDCDCDBB64B32B3E5BE744DC14B12AA2E8DE8AB6BC83E7887C9DB3052864854216909F85FC3C702FFEFE3C2EF0DE58AFD7FF392EF2E2B07A701C9F18C7DF8BE38B63BC26E29EF22E89E5F1D8EF62FC55BCE6BF63FCF78883E2B14A8C1F6E341A7F5BA954767256E929174E2DB78EED2A1B0BBD894B73E154670F50C80A852C203F0BF97990AA542A7FD2D2D2F2EAB8007C57A3D1F8971847451C15715AF91BEA05E56FA91F2AE3E688CBCBE74EAB56AB47E7D7E4D7C6F1BBE3F83579E785333B78C5CF78D7888B9E46736153B1203E7BBB399B804256286401F959C8CF43BB29B1535E1CE65D0E79B743BD5E1F1BC713CBBB207E157175DE1D51DE2591774B5C5BDE3DF1BD8849F1BA43F2EE8ABCCB22DEE74D79D785B33AB0C4CFB356AE17F278D3207E8EC589279E58CC9D3BB7B8E38E3B8A071F7CB048AB57AF2E3A3A3A8A850B171653A64C295A5B5B9FD06C88CFC143EE6E0014B242210BC8CF427EE629E5BA0F7101F9CAB8287D67AE0791EB42C4F191B94E44AE17118F5D16E38DE53A126B2296465C91EB4C94EB4D1C9BEB4FE43A1471FCDE9CDBDFDEDEFE7C6776BB3719F2E7B1BE678361DAB46945676767D11B2B57AEDCF0FAFCBA8D1A0EFB39BB804256286401F9597E86AD2277C868341A7F933B66E4CE1971E1F9E588E373478D72678DDF963B6D3C5CFE26FDF7B91347EEC811AF9F1C17BF87E54E1DB963471CBF3977F07056B74993A1D6B3C970D861876DB87BE199C8AF1B376EDCC6CD067736000A59A19005E467F919FAD4732B95CACB9B9B9BDF1E17BD9F8C181917A7FF1AE39418A7E72283113744DC5F2E4E785BC4A272C7833322C6C7EB46C738A2D168FC43BCD75F45BCC0697D6AE59A0C8F4F97183F7EFCE3D3239EA99C563171E2C48DA751ECEE6C030A59A19005E467F919FA9DB6B6B61DF3E2382E5CDF1FE3E7AAD5EAFE71FC6F71FCED38FE458CBF89B823625DC48A78EEFA18E7C4382D9EFF8FDC8A31C6E6183F168FBD75E4C8912F1BCAE7B3DC59E2F13B199E6D93A167B361A33B1B16F8F4020A59A19005E467F91906B2E76613219B094D4D4D1FCFDBF7CB2643361B7E58361FB209F1C7B2297147D9A4B820E2CC6C5E944D8CBD62FCC76C6E649363A0FCCFE7DA19B986C6533419F6EAB926C3339D2EB1A569143DD76C30850250C80A852C203FCBCF30240C1F3EFC8539DD22A75D54ABD53DCB6918E3E3F85B319E9FD334CAE91A6BE3B195E5348E9CCEF1A388AFE7348F185B63FC444EFFC86920D9E8D89EFF4FF17D7EA7BCC03F3BBEAF9D37D36878FC6E865CC8715B983E7DBABB1A0085AC50C802F2B3FC0C6C4EA3D1F8CB5C98322ED23F1A17CE8D72C1CAC971FC83F2C23D17B4BCAFBC4BE2AE72C1CB19E5857F2E84F9E538FE7C8C1FC8853273C1CC6DF17DE69FD97D819FCD918DEF26C8DD3EBA1780CCBB0E56AC58B14D1A0DB91BC5465B5FEEEA53042864854216909FE567E069CA2D3BCB8BF9F7C645FE1E317E29B7F68CF866C4CFCB2D3F97965B803E506E099A5B83FE38C66FE496A131EED368343E556E25FACADC5AF469341AAEECB9F34319F9D8B07C3EDEF3C0EEC7274F9E5C6C4B53A64CE9F93D1CE1D3012864854216909FE567601BAA542A2F897853360172ABC97ABD7E481C4F8A716A8C97445C1BD1596E03BA2CE2EA78DD85317E376262BC6E6C8C7BC7F8A1789FDD5A5B5B5F1CFF7DEB261A0DDD7146392564C37FCF9B376F9B361A162E5CD8F3CF9EE1270E2864854216909FE567A01F18366CD80E71A1FE9A6AB5FAEE183F13B16F1C1F1DE3E931FE2CC6CB236E8E581DF1D8161A0DB930E3A3DDC75B7B11C88D757474F4FCB3EFF4930414B242210BC8CFF2333080542A959DB6D46428D78F78BC11B1B5B6B4DCD256973DFEEC2E3F2140212B14B280FC2C3F0303ABD1B0DB661A0C0B72DBCB8D1B117DA1E79FE727042864854216909FE5676000C9B51A7A4E55C8DD2EE2B1DD7BBE261E5BE58E060085AC4216909F85FC0C3CA55C50329C53AFD7F76A6B6BDB7153AF89E76FB24603804256210BC8CF427E06B68A6C44D875024021AB9005E467213F035B45B55A3DB0FBE27FF2E4C9DBB4D13065CA949E8D86239C7D40212B14B280FC2C3F03834C7373F32E71D1BF3E2FFEE3B858B162C5366932AC5CB9B2686D6DEDD968D8D5D90714B242210BC8CFF2333008D5EBF559DD0D8069D3A66D9346C3F4E9D39FB0EB85B30E0C2A73E6CC5140F69FE88A4276BD4F25203FCBCFC0766D34ECD5DD04C8BB1AB6F6A290F97EF9BEDD7F46FC792DCE3A30A8CC9F3F7F796767A722B21F444747C78FA390BDC1A712909FE56760FBAAD56A977637020E3DF4D0C7B6D65697B9A5E5B871E39E7037C3B061C37670C6814165EEDCB97BCE9B37EFFE7BEEB9678D6272FBFDA62C8BD8D9B367DF1EB1B74F25203FCBCFC0766F34EC1A716F774360FCF8F1C5B36D36649361E2C4893D9B0CABEAF5FAEECE36302845F1F4D959B3665D99B785E61C54D1E791E7FD06452C203FCBCF40FF51AD566BDD0B4376DFD9F04CA751E4D76D742783291300000030D4D46AB5B69ECD865C5B211788CC5D237ABBBB442EFCD8734D868C6AB57AA4B30B0000004350DED910B1B267A3201B0793274F2EE6CD9BB7E16E85EE6915393DA2A3A3A358B8706171EAA9A7166D6D6D4F6830E474896C5E38AB0000003084E59A0D3DB7BD7C86B1C09A0C000000C0E36AB5DADE65C361FDD36930E45D0C76970000000036A9B9B979977ABD7E48AD563B27E2D688AEB2A990E39D1133228EC83B219C2D0000000000000000000000000000000000000000000000000000000000000000FA93FF03F003D6AD405DFFBD0000000049454E44AE426082";
        System.out.println(Base64.getEncoder());
    }

    
    
    @Test   
    public void characterReplacement() {
        int k = 3;
        String s = "ACJIDNJSKSF";
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        System.out.println( right - left);
    }

    @Test
    public void num222() {
        System.out.println(numWays(4));
    }

    //使用哈希map，充当备忘录的作用
    Map<Integer, Integer> tempMap = new HashMap();

    public int numWays(int n) {
    // n = 0 也算1种
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
    //先判断有没计算过，即看看备忘录有没有a
        if (tempMap.containsKey(n)) {
    //备忘录有，即计算过，直接返回
            return tempMap.get(n);
        } else {
    // 备忘录没有，即没有计算过，执行递归计算,并且把结果保存到备忘录map中，对1000000007取余（这个是leetcode题目规定的）
            tempMap.put(n, (numWays(n - 1) + numWays(n - 2)) % 1000000007);
            return tempMap.get(n);
        }
    }
    
    @Test
    public void twoS(){
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, 9)));
    }
    
    
    private int[] twoSum(int[] nums,int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[] { map.get(temp), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("发生错误");
    }
    
    @Test
    public void testStart11(){
        String s = "abcabcbb";
        System.out.println(str11(s));
    }
    
    private int str11(String s){
        int length = s.length();
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int start = 0, end = 0; end < length; end++){
            char element = s.charAt(end);
            if(map.containsKey(element)){
                start = Math.max(map.get(element  ) + 1, start); //map.get()的地方进行+1操作
            }
            max = Math.max(max, end - start + 1);
            map.put(element, end);
        }
        return max;
    }
    
    
    
}










