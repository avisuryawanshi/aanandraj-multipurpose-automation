package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ✅ ExcelDataGenerator
 * Utility to generate the Excel file with test data for data-driven testing
 * Run this once to create the Excel file with test data
 */
public class ExcelDataGenerator {

    public static void main(String[] args) {
        String filePath = "src/test/resources/testdata/invalidLoginData.xlsx";
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TestData");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Username");
            headerRow.createCell(1).setCellValue("Password");
            headerRow.createCell(2).setCellValue("Description");
            
            // Style header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            for (int i = 0; i < 3; i++) {
                headerRow.getCell(i).setCellStyle(headerStyle);
            }
            
            // Add test data rows
            Object[][] testData = {
                {"", "", "TC003 - Empty username & password"},
                {"$%&@!*", "1234", "TC004 - Special characters in username"},
                {"tester", "12", "TC005 - Short password"},
                {"tester", "", "TC006 - Only username"},
                {"", "1234", "TC007 - Only password"},
                {"thisisaverylongusernameexceedinglimit", "123456", "TC008 - Long input"}
            };
            
            int rowNum = 1;
            for (Object[] data : testData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue((String) data[0]);
                row.createCell(1).setCellValue((String) data[1]);
                row.createCell(2).setCellValue((String) data[2]);
            }
            
            // Auto-size columns
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            
            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("✅ Excel file created successfully: " + filePath);
            }
            
        } catch (IOException e) {
            System.err.println("❌ Error creating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

