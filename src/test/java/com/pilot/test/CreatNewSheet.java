package com.pilot.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

public class CreatNewSheet {
    public static void main(String[] args) throws Exception {
        Workbook wb = new HSSFWorkbook();
        wb.createSheet("第一个工作页");
        wb.createSheet("第二个工作页");
        FileOutputStream fileOut = new FileOutputStream("G:\\POI创建工作页Sheet");
        wb.write(fileOut);
        fileOut.close();
    }
}
