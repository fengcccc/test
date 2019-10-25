package com.pilot.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

public class CreatSheet {
    public static void main(String[] args) throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("海星");
        row.createCell(1).setCellValue("nice");
        row.createCell(2).setCellValue(1);
        row.createCell(3).setCellValue(1.6);
        row.createCell(4).setCellValue(false);
        FileOutputStream fileOut = new FileOutputStream("G:\\用POI输出的Cell");
        wb.write(fileOut);
        fileOut.close();
    }
}
