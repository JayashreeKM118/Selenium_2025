package org.jkm.com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    private static final String TEST_DATA_SHEET_PATH = "./src/test/java/resources/TestdataSele.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {

        Object[][] data = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(TEST_DATA_SHEET_PATH);

            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for(int i=0; i<sheet.getLastRowNum();i++) {
                for(int j=0; j<sheet.getRow(0).getLastCellNum();j++)
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
