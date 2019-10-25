package com.pilot.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

//导出工作簿
public class Fileout {
    public static void main(String[] args) throws Exception {
        Workbook wb = new HSSFWorkbook();//定义一个工作簿
        FileOutputStream fileOut = new FileOutputStream("G:\\用POI输出的工作簿.xls");
        wb.write(fileOut);
        fileOut.close();

    }
}
