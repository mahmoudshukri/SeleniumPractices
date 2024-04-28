//package Data;
//
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class ExcelReader {
//    private FileInputStream fis; // Declare fis as an instance variable
//
//    public FileInputStream getFileInputStream() {
//        String filePath = System.getProperty("user.dir") + "/src/test/java/Data/UserRegData.xlsx";
//        File srcFile = new File(filePath);
//        try {
//            fis = new FileInputStream(srcFile);
//        } catch (FileNotFoundException e) {
//            // Print the error message
//            System.out.println("Test Data File not found: " + filePath);
//            // Print the stack trace for debugging purposes
//            e.printStackTrace();
//        }
//        return fis;
//    }
//
//    public Object[][] getExcelData() throws IOException {
//        getFileInputStream(); // Call getFileInputStream to initialize fis
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet sheet = wb.getSheetAt(0);
//        int TotalNumberOfRows = sheet.getLastRowNum() + 1;
//        int TotalNumberOfColumns = 4;
//        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfColumns];
//        for (int i = 0; i < TotalNumberOfRows; i++) {
//            for (int j = 0; j < TotalNumberOfColumns; j++) {
//                XSSFRow row = sheet.getRow(i);
//                arrayExcelData[i][j] = row.getCell(j).toString();
//            }
//        }
//
//
//        wb.close();
//        return arrayExcelData;
//    }
//}
package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private FileInputStream fis; // Declare fis as an instance variable

    public FileInputStream getFileInputStream() {
        String filePath = System.getProperty("user.dir") + "/src/test/java/Data/UserRegData.xlsx";
        File srcFile = new File(filePath);
        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test Data File not found: " + filePath);
            e.printStackTrace();
        }
        return fis;
    }

    @SuppressWarnings("resource")
    public Object[][] getExcelData() throws IOException {
        getFileInputStream(); // Call getFileInputStream to initialize fis
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        // Handle potential null sheet (optional)
        if (sheet == null) {
            System.out.println("Error: Sheet not found at index 0!");
            return new Object[0][0]; // Return an empty array in case of missing sheet
        }

        int totalRows = 2; // Since you know there are only 2 rows
        int totalColumns = 4; // And 4 columns
        String[][] arrayExcelData = new String[totalRows][totalColumns];

        for (int i = 0; i < totalRows; i++) {
            XSSFRow row = sheet.getRow(i);

            // Handle potential empty row (optional, can be removed if not expected)
            if (row == null) {
                System.out.println("Warning: Empty row encountered at index " + i);
                continue; // Skip to the next row
            }

            for (int j = 0; j < totalColumns; j++) {
                XSSFCell cell = row.getCell(j);

                // Handle potential missing cell (optional, can be removed if not expected)
                if (cell != null) {
                    arrayExcelData[i][j] = cell.toString();
                } else {
                    arrayExcelData[i][j] = ""; // Assign an empty string for missing cells
                }
            }
        }

        wb.close();
        return arrayExcelData;
    }
}


