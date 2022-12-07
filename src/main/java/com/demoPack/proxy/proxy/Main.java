package com.demoPack.proxy.proxy;


import com.shiyue.springboot.domain.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Proxy;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    //public static void main(String[] args) {
    //    Student student = new Student();
    //    // 代理类的动作
    //    InvocationImpl invocation = new InvocationImpl(student);
    //    ClassLoader classLoader = student.getClass().getClassLoader();
    //    Class<?>[] interfaces = student.getClass().getInterfaces();
    //    Study proxyInstance = (Study) Proxy.newProxyInstance(classLoader,interfaces , invocation);
    //    System.out.println("动态代理对象的类型："+proxyInstance.getClass().getName());
    //    proxyInstance.read();
    //    System.out.println("---------------------------------");
    //    proxyInstance.write();
    //
    //    createProxyClassFile();
    //}

    public static void main(String[] args) {
        Student student = new Student();
        InvocationImpl invocation = new InvocationImpl(student);
        ClassLoader classLoader = student.getClass().getClassLoader();
        Class<?>[] interfaces = student.getClass().getInterfaces();
        Study proxyInstance = (Study) Proxy.newProxyInstance(classLoader, interfaces, invocation);

        proxyInstance.write();

        //代理两层
        Study proxyProxyInstance = (Study)Proxy.newProxyInstance(proxyInstance.getClass().getClassLoader(),
            proxyInstance.getClass().getInterfaces(),
            new ProxyInvocationImpl(proxyInstance));
        System.out.println("动态代理对象的类型："+proxyInstance.getClass().getName());
        proxyProxyInstance.read();
        System.out.println("---------------------------------");
        proxyProxyInstance.write();
    }


//    private static void createProxyClassFile() {
//        String name = "StudentProxy";
//        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Study.class});
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(name + ".class");
//            out.write(data);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != out) try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private final static String REGEX_POSITIVEINTEGER= "^\\+?[1-9][0-9]*$";


    @Test
    public void swtest(){
        System.out.println("".matches(REGEX_POSITIVEINTEGER));
    }


    @Test
    public void downloadExcelTemplate(){
        try {
            File file = new File("D:/select1new.xlsx");

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));

            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
// 获得sheet里所有的行数
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

            XSSFRow row  ;
// 循环每个行
            StringBuilder stringBuilder = new StringBuilder("(");
            for (int i = 1;i<physicalNumberOfRows;i++) {
                row = sheet.getRow(i);
                stringBuilder.append("'").append(row.getCell(1).getStringCellValue()).append("', \n");
            }
            stringBuilder.append(")");
            System.out.println(stringBuilder.toString());
        }catch (Exception e){
            System.out.println("下载模板异常");
            e.printStackTrace();
        }
    }


    @Test
    public void downloadExcelTemplate1(){
        try {
            File file = new File("D:/请导出指定课程数据章节数等字段_221017.xlsx");
            File file2 = new File("D:/sss.xlsx");
//            File file3 = new File("D:/select3.xlsx");
//            File file4 = new File("D:/select1new.xlsx");

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

            XSSFWorkbook xssfWorkbook2 = new XSSFWorkbook(new FileInputStream(file2));
            XSSFSheet sheet2 = xssfWorkbook2.getSheetAt(0);
            int physicalNumberOfRows2 = sheet2.getPhysicalNumberOfRows();
            Map<String,Object> map = new HashMap();
            List<Map<String,Object>> list = new ArrayList();
            XSSFRow row2  ;


//            XSSFWorkbook xssfWorkbook3 = new XSSFWorkbook(new FileInputStream(file3));
//            XSSFSheet sheet3 = xssfWorkbook3.getSheetAt(0);
//            int physicalNumberOfRows3 = sheet3.getPhysicalNumberOfRows();

            XSSFRow row  ;
// 循环每个行
            for (int i = 1;i<physicalNumberOfRows;i++) {
                row = sheet.getRow(i);
                for (int j = 0;j<physicalNumberOfRows2;j++){
                    row2 = sheet2.getRow(j);
                    if (row.getCell(2).getStringCellValue().equals(row2.getCell(1).getStringCellValue())){
                        row2.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        row2.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        row.getCell(3).setCellValue(row2.getCell(2).getStringCellValue());
                        row.getCell(4).setCellValue(row2.getCell(4).getStringCellValue());
                        break;
                    }
                }

            }

            FileOutputStream out = new FileOutputStream(new File("D:/ss.xlsx"));
            xssfWorkbook.write(out);
        }catch (Exception e){
            System.out.println("下载模板异常");
            e.printStackTrace();
        }
    }



    @Test
    public void testFil(){
        List<User> users = new ArrayList<>();
        users.add(new User(3,0));
        users.add(new User( 3,0));
        users.add(new User(0,4));
        users.add(new User(2,0));
        users.add(new User( 0,0));
        System.out.println(users.stream().filter(u->
                u.getAge()>0||u.getId()>0).collect(Collectors.toList()));
        System.out.println(users.stream().filter(u->
                u.getAge()>0||u.getId()>0).collect(Collectors.toList()).size());
    }


    @Test
    public void csvtest(){
        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:/T0011_20221101120000_20221100_002.CSV"))) {
            char cf = 0x1F;
            String line;
            List<String> cfset = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                try {
                    int icf = line.indexOf(cf);
                    if (icf!=-1){
                        cfset.add(line);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(cfset.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void csvtest1(){
        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:/T0011_20221101120000_20221100_002.CSV"))) {
            char cf = 0x1F;
            String line;
            List<String> cfset = new ArrayList<>();
            int i = 1;
            while ((line = br.readLine()) != null) {
//                if (i==2){
//                    System.out.println(line.substring(0,36));
//                }
//                i++;

                try {
                    UUID.fromString(line.substring(0,36));
                    cfset.add(line);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(cfset.size());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    @Test
    public void ssdwadwa () throws Exception{
//        Number m = null;
//        NumberFormat nf = NumberFormat.getPercentInstance();
//        m = nf.parse(new Integer(12).toString());
//        System.out.println(m);
        // 按行读取
        char ca = 0x0A;
        char cf = 0x1f;
        String a = "知道  知道\n ";
        System.out.println(a.indexOf(cf));
        System.out.println(a.indexOf(ca));
    }

    @Test
    public void html () throws Exception{
        String a = "<span style=\\\"font-family:等线;font-size:10.5pt;\\\">本专题就公文基本知识、行文规则、公文格式等进行了详细全面的讲解，有助于员工进一步掌握公文写作方法，提高公文写作素质。同时开设企业新闻写作操作指南选修课，帮助员工掌握新闻写作的技巧，增强新闻的传播力和影响力。</span>";
        Document doc = Jsoup.parseBodyFragment(a);
        System.out.println(doc);
        List<Element> e_a =doc.getElementsContainingText("span");
        System.out.println(e_a);
    }

}
