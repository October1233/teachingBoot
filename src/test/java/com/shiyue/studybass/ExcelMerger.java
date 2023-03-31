package com.shiyue.studybass;
// 1. Try generating with command K. Ask for a new react component of an error popup.
// 2. Then, select the outputted code and hit chat. Ask if there's a bug. Ask how to improve.
// 3. Try selecting some code and hitting edit. Ask the bot to add a button that updates a statefield.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

public class ExcelMerger {


    public static void main(String[] args) {
        try {
            FileInputStream file1 = new FileInputStream(new File("D:/dataWangda/工作1.xlsx"));
            FileInputStream file2 = new FileInputStream(new File("D:/dataWangda/工作2.xlsx"));
            XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
            XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
            XSSFSheet sheet1 = workbook1.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(0);
            int physicalNumberOfRows1 = sheet1.getPhysicalNumberOfRows();
            int physicalNumberOfRows2 = sheet2.getPhysicalNumberOfRows();
            Map<String,Integer> map = new HashMap<>();
            for (int j = 0; j < physicalNumberOfRows2; j++) {
                XSSFRow row2 = sheet2.getRow(j);
                if (!row2.getCell(1).getStringCellValue().equals(""))
                map.put(row2.getCell(1).getStringCellValue(),j);
            }
            System.out.println(map.size());
            for (int i = 1; i < physicalNumberOfRows1; i++) {
                XSSFRow row1 = sheet1.getRow(i);
                String cell1 = row1.getCell(1).getStringCellValue();
                System.out.println(cell1);
                System.out.println(map.get(cell1));
                if (map.get(cell1)==null){
                    continue;
                }
                XSSFRow row2 = sheet2.getRow(map.get(cell1));

                if(row2.getCell(0) != null) {
                    Cell cell = row2.getCell(0);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    row1.createCell(13).setCellValue(row2.getCell(0).getStringCellValue());
                }
                if(row2.getCell(1) != null) {
                    Cell cell = row2.getCell(1);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    row1.createCell(14).setCellValue(row2.getCell(1).getStringCellValue());
                }
                if(row2.getCell(2) != null) {
                    Cell cell = row2.getCell(2);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    row1.createCell(15).setCellValue(row2.getCell(2).getStringCellValue());
                }
                if(row2.getCell(3) != null) {
                    Cell cell = row2.getCell(3);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    row1.createCell(16).setCellValue(row2.getCell(3).getStringCellValue());
                }
                if(row2.getCell(4) != null) {
                    Cell cell = row2.getCell(4);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    row1.createCell(17).setCellValue(row2.getCell(4).getStringCellValue());
                }
            }
            file1.close();
            file2.close();
            FileOutputStream outputStream = new FileOutputStream("D:/dataWangda/工作3.xlsx");
            workbook1.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void Strings() throws Exception{
        FileInputStream file1 = new FileInputStream(new File("D:/dataWangda/工作3.xlsx"));
        XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);
        int physicalNumberOfRows1 = sheet1.getPhysicalNumberOfRows();
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();
        for (int i=1;i<physicalNumberOfRows1;i++){
            XSSFRow xssfRow = sheet1.getRow(i);
            if (!getN(xssfRow,0).equals(getN(xssfRow,17))){
                xssfRow.createCell(19).setCellValue("姓名不一致");
            }
            if (!getN(xssfRow,3).equals(getN(xssfRow,16))){
                xssfRow.createCell(20).setCellValue("手机号不一致");
            }
            if (map.containsKey(getN(xssfRow,3))){
                sheet1.getRow(map2.get(getN(xssfRow,3))).createCell(21).setCellValue("手机号重复"+i);
                xssfRow.createCell(21).setCellValue("手机号重复"+i);
            }
            map2.put(getN(xssfRow,3),i);
            if (map2.containsKey(getN(xssfRow,1))){
                sheet1.getRow(map2.get(getN(xssfRow,1))).createCell(22).setCellValue("ihrcode重复"+i);
                xssfRow.createCell(22).setCellValue("ihrcode重复"+i);
            }
            map2.put(getN(xssfRow,1),i);

        }
        file1.close();
        FileOutputStream outputStream = new FileOutputStream("D:/dataWangda/工作4.xlsx");
        workbook1.write(outputStream);
    }

    private String getN(XSSFRow xssfRow,Integer o){
        if (xssfRow.getCell(o)!=null){
            return xssfRow.getCell(o).getStringCellValue();
        }
        return "";
    }
}