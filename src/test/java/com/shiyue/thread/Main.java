package com.shiyue.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shiyue.springboot.domain.CertificatePrivacy;
import com.shiyue.springboot.domain.User;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
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


@Log4j
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
            log.error("外层捕获",e);
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
    public void eq(){
        User user = new User();
        User user1 = new User();
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
                log.error(e);
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

}










