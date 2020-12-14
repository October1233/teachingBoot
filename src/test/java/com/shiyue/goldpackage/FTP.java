package com.shiyue.goldpackage;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.misc.IOUtils;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FTP {

    FtpClient ftpClient;


    /**
     *      * 连接FTP服务
     *      * @param url //IP地址
     *      * @param port//端口号
     *      * @param username//用户名
     *      * @param password//密码
     *      * @return
     *     
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp 
        FtpClient ftp = null;
        try {
            //创建地址 
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接 
            ftp = FtpClient.create();
            ftp.setConnectTimeout(6*1000);
            ftp.connect(addr);
            //登陆 
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     *      * 取ftp上的文件内容
     *      * @param ftpFile
     *      * @param ftp
     *      * @return
     *     
     */
    public static List<String> download(String ftpFile, FtpClient ftp) {
        List<String> list = new ArrayList<String>();
        String str = "";
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 获取ftp上的文件 
            is = ftp.getFileStream(ftpFile);
//转为字节流
            br = new BufferedReader(new InputStreamReader(is));
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            br.close();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


//    public static void main(String[] args) {
//
//        try {
//            System.out.println(new Date());
//            FtpClient ftp = connectFTP("120.77.147.199",86,"cmbc_credit","cmbc_credit_4EMcck");
//            System.out.println(new Date());
//            List<String> list = download("credit_report_9060_20200521.002.xlsx.result.20200521174650609.txt",ftp);
//            for(int i=0;i<list.size();i++){
//                String strOut = null;
//                if(list.get(i).toString()!=null){
//                    try {
//                        byte[] bs = list.get(i).toString().getBytes();
//
//                        strOut = new String(bs, "utf-8");
//                    } catch (UnsupportedEncodingException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(strOut);
//                System.out.println(list.get(i).toString());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }


//    public byte[] getInputStream(String ftpDirName, String ftpFileName) {
//        try {
//            if ("".equals(ftpDirName)) {
//                ftpDirName = "/";
//            }
//            String dir = new String(ftpDirName.getBytes("GBK"),
//                    "iso-8859-1");
//            if (!ftp.changeWorkingDirectory(dir)) {
//                System.out.println("切换目录失败：" + ftpDirName);
//                return null;
//            }
//            // 一定要加上字符集指定，因为获取文件时有中文，会出现乱码而获取不到。
//            String fileName = new String(ftpFileName.getBytes("GBK"),
//                    "iso-8859-1");
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，ftp server可能每次开启不同的端口来传输数据，
//            // 但是在Linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞。
//            ftp.enterLocalPassiveMode();
//            InputStream inputStream = ftp.retrieveFileStream(fileName);
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            ftp.getReply();
//            ftp.logout();
//            return bytes;
//        } catch (Exception e) {
//            log.error("获取文件流出现异常", e);
//            return null;
//        }
//        Map<String,Map<String,List<Map<String,String>>>>
//    }

    public static void main(String[] args) {
        System.out.println(deleteFile("120.77.147.197", "cmbc_credit_4EMcck", "cmbc_credit", 86, "/credit_feedback/", "credit_report_9060_20200612.001.xlsx.result.20200612102223604.txt"));
    }

    public static boolean deleteFile(String ftpHost, String ftpPassword, String ftpUserName,int ftpPort,String pathname, String filename){
        FTPClient ftp = new FTPClient();
        boolean flag = false;
        try {
            //切换FTP目录
            int reply;
            ftp.setConnectTimeout(60*1000);
            System.out.println(new Date());
            ftp.connect(ftpHost, ftpPort);

            System.out.println(new Date());
            ftp.login(ftpUserName, ftpPassword);
            System.out.println(new Date());
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftp.disconnect();
                return false;
            }
            ftp.setControlEncoding("UTF-8"); // 中文支持
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
//            ftp.execPBSZ(0);
//            ftp.execPROT("P");
            ftp.type(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            System.out.println("2");
            ftp.changeWorkingDirectory(pathname);
            //int dele = ftp.dele(filename);
            flag=ftp.deleteFile(filename);
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(new Date());
            if(ftp.isConnected()){
                try{
                    ftp.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }




    @Test
    public void ssssssss() {
        String ss = "credit_report_9060_20200525.002.xlsx.result.20200525105001898.txt.ok";
        String cc = "credit_report_9060_20200518.003.xlsx.result.20200518093738835.txt";
        String ff = "credit_report_9060_20200518.003.xlsx";
        System.out.println(ss.length() + "---" + cc.length() + "---" + ff.length());
        if (ss.length() == 68) {
            System.out.println("success");
        }
        System.out.println(ss.substring(0,36));
        System.out.println(ss.substring(ss.length() - 2));

    }


    @Test
    public void sssstssss() throws ParseException {
        String serialNum = "0111" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+(int)(Math.random()*900)+100;
        System.out.println(serialNum);
        String serialNum1 = "0111" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+(int)(Math.random()*900)+100;
        System.out.println(serialNum1);
        String serialNu11m = "0111" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+(int)(Math.random()*900)+100;
        System.out.println(serialNu11m);
    }


//    public static void main(String [] srgs)
//    {
//        int i=(int)(Math.random()*900)+100;
////int i= new java.util.Random().nextInt(900)+100;也可以
//        System.out.println(i);
//
//    }


    @Test
    public void test(){
        System.out.println(createNumber("credit_report_9060_20200529.005"));

    }

    private static String createNumber(String fileName) {
        String uid_end = "";
        if (fileName != null) {
            String[] files = fileName.split("\\.");
            String maxUid = files[1];// 001
            int endNum = Integer.parseInt(maxUid); // 把String类型的001转化为int类型的1
            int tmpNum = 1000 + endNum + 1; // 结果1002
            uid_end = subStr("" + tmpNum, 1);// 把1002首位的1去掉，再拼成0190710002字符串
        }else {
            uid_end = "001";
        }
        return uid_end;
    }
    private static String subStr(String str, int start) {
        if (str == null || str.equals("") || str.length() == 0)
            return "";
        if (start < str.length()) {
            return str.substring(start);
        } else {
            return "";
        }
    }

    private static Long orderNumber = 0l;

    public String gerSeriaNumber() {

        synchronized (orderNumber) {

            long l = System.nanoTime();
            //等到下一纳秒
            while (l == orderNumber) {
                l = System.nanoTime();
            }

            orderNumber = l;
        }
        return String.valueOf(orderNumber);
    }

    @Test
    public void test1() {

        long start = System.currentTimeMillis();
        String num = "";

        for (int i = 0; i < 5000000; i++) {
            num = gerSeriaNumber();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时（毫秒）：" + (end - start));
    }



    }











