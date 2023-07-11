package com.jalavathi.utils.generics;

import com.jalavathi.utils.GlobalConstants;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ReadTestData {
    public static HashMap<String,String> getSheetData(String fileName, String sheetName) throws IOException {
        HashMap<String,String> testData = new HashMap<>();
        FileInputStream fis = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(fis);
        if(null == sheetName && sheetName.isEmpty())
            return null;
        Sheet sheet = workbook.getSheet(sheetName);
        for(int i=0;i<sheet.getLastRowNum();i++){
            Row r = sheet.getRow(i);
            Cell c1 = r.getCell(0);
            String cell1Value= getStringCellValue(c1);
            Cell c2 = r.getCell(1);
            String cell2Value= getStringCellValue(c2);
            testData.put(cell1Value,cell2Value);
          }
        return testData;
    }

    public static String getStringCellValue(Cell c1){
        String cellValue= "";
        try{

            switch(c1.getCellType()){
                case BLANK:
                    break;
                case STRING:
                    cellValue = c1.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    cellValue = String.valueOf(c1.getNumericCellValue());
                case BOOLEAN:
                    cellValue = String.valueOf(c1.getBooleanCellValue());

            }

        }
        catch(Exception e){
            cellValue = "";
        }
        return cellValue;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(getSheetData(GlobalConstants.TEST_DATA_FILE_NAME,GlobalConstants.Login_SHEET_NAME).get("UserName"));
    }
}
