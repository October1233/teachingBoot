package com.shiyue.servlet;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class download {

    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response){
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet();
            XSSFRow xssfRow = sheet.createRow(0);
            xssfRow.createCell(0).setCellValue("班级（专业）");
            xssfRow.createCell(1).setCellValue("姓名");
            xssfRow.createCell(2).setCellValue("性别");
            xssfRow.createCell(3).setCellValue("年龄");
            xssfRow.createCell(4).setCellValue("学号");
            xssfRow.createCell(5).setCellValue("身份证号码");
            xssfRow.createCell(6).setCellValue("入学时间");
            xssfRow.createCell(7).setCellValue("备注");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("学生信息导出"+System.currentTimeMillis(), "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            xssfWorkbook.write(response.getOutputStream());
        }catch (Exception e){
            System.out.println("下载模板异常");
            e.printStackTrace();
        }
    }
}
