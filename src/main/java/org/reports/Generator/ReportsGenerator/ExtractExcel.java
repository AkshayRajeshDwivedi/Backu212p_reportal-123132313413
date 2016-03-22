package org.reports.Generator.ReportsGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;



class  ExtractExcel {

  public static List<String> readExcel(File file) throws IOException{

    List<String> names=new ArrayList<String>();
    String name=null;
  
    FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
    Workbook getWorkbook = null;
    getWorkbook = new HSSFWorkbook(inputStream);
    Sheet getSheet = getWorkbook.getSheet("Sheet1");
    int rowCount = getSheet.getLastRowNum()-getSheet.getFirstRowNum();

    Row row = getSheet.getRow(1);
    int i=1;
    while ((row.getCell(5))!=null) {

          name=row.getCell(1).getStringCellValue()+" "+row.getCell(0).getStringCellValue();
          names.add(name);
          i=i+1;
          row = getSheet.getRow(i);

    }     
 
   return names;
  }



}
