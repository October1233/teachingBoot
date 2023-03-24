package com.shiyue.springboot.controller;


import com.demo.util.POIUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequestMapping("/xslxSheet")
@Controller
public class XslxSheetController {


//    String pcname = "/app/targetExcel.xlsx";

    String pcname = "D:/targetExcel.xlsx";

    @PostMapping("/xslxSheet")
//    @ResponseBody
    public void importExp(@RequestParam("file") MultipartFile file, HttpServletRequest request
            , HttpServletResponse response) {
        // 判断文件是否为空
        String flag = "02";//上传标志
        if (!file.isEmpty()) {
            try {
                String rowNum = request.getParameter("row");
                System.out.println(rowNum);
                String originalFilename = file.getOriginalFilename();//原文件名字
                InputStream is = file.getInputStream();//获取输入流
                XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(is);
                XSSFSheet sheet = XSSFWorkbook.getSheetAt(0);
                int physicalNumberOfRows2 = sheet.getPhysicalNumberOfRows();
                XSSFRow rowStr;
                Map<String,Integer> mapMark = new HashMap<>();
                int initRow = 1;
                System.out.println(physicalNumberOfRows2);
                for (int i = 1;i<=physicalNumberOfRows2-200;i++){
                    log.info("数据MAP："+mapMark);
                    rowStr = sheet.getRow(i);
                    if (rowStr==null){
                        continue;
                    }
                    XSSFCell indexCell = rowStr.getCell(Integer.parseInt(rowNum));
                    String index = "";
                    if (indexCell!=null){
                        index = indexCell.getStringCellValue().toUpperCase();
                    }else {
                        continue;
                    }
                    if (!mapMark.containsKey(index)){
                        XSSFSheet XSSFSheet = initSheet(index,XSSFWorkbook);
                        XSSFRow rowStr2 = XSSFSheet.createRow(initRow);
                        for (int j = 0;j<rowStr.getPhysicalNumberOfCells();j++){
                            POIUtils.copyCell(XSSFWorkbook,rowStr.getCell(j),rowStr2.createCell(j),true);
                        }
                        Integer intMark = initRow;
                        intMark++;
                        mapMark.put(index,intMark);

                    }else {
                        Integer intNew = mapMark.get(index);
                        XSSFSheet XSSFSheet = XSSFWorkbook.getSheet(index);
                        XSSFRow rowStr2 = XSSFSheet.createRow(intNew);
                        for (int j = 0;j<rowStr.getPhysicalNumberOfCells();j++){
                            POIUtils.copyCell(XSSFWorkbook,rowStr.getCell(j),rowStr2.createCell(j),true);
                        }
//                        POIUtils.copyRow(XSSFWorkbook,rowStr,XSSFSheet.createRow(intNew),true);
                        intNew++;
                        mapMark.put(index,intNew);
                    }
                }
                log.info("正在生成文件");
//                String filename = originalFilename;

                FileOutputStream fos = new FileOutputStream(pcname);
                XSSFWorkbook.write(fos);
                fos.close();
//                response.setHeader("content-Type", "application/vnd.ms-excel");
////                response.setContentType("application/ms-excel;charset=UTF-8");
////                response.setHeader("Content-Disposition", "attachment;filename="
////                        .concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
////                XSSFWorkbook.write(response.getOutputStream());
            } catch (Exception e) {
                flag = "03";//上传出错
                log.error("异常",e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Integer(1).equals(null));
        Integer a = null;
        System.out.println(a.equals(new Integer(1)));
    }


    private XSSFSheet initSheet(String sheetName, XSSFWorkbook XSSFWorkbook){
        XSSFSheet sheet = XSSFWorkbook.getSheetAt(0);
        XSSFRow rowStr = sheet.getRow(0);
        XSSFSheet sheetLess = XSSFWorkbook.createSheet(sheetName);
        XSSFRow rowStr2 = sheetLess.createRow(0);
        for (int j = 0;j<rowStr.getPhysicalNumberOfCells();j++){
            POIUtils.copyCell(XSSFWorkbook,rowStr.getCell(j),rowStr2.createCell(j),false);
        }
//        POIUtils.copyRow(XSSFWorkbook,rowStr,rowStr2,true);
        return sheetLess;
    }



    @GetMapping("/downloadLocal")
    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        // 读到流中
        InputStream inStream = new FileInputStream(pcname);// 文件的存放路径
        File file = new File(pcname);
        if (!file.exists()) {
            return;
        }
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=targetExcel.xlsx");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
