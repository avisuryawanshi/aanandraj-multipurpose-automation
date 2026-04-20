package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ✅ ExcelDataReader
 * Utility class to read data from Excel files for data-driven testing
 * Supports .xlsx (Excel 2007+) format using Apache POI
 */
public class ExcelDataReader {

    private static final Logger log = LogManager.getLogger(ExcelDataReader.class);

    /**
     * Reads data from an Excel file and returns it as a 2D Object array
     * 
     * @param filePath Path to the Excel file (e.g., "src/test/resources/testdata/invalidLoginData.xlsx")
     * @param sheetName Name of the sheet to read from
     * @param startRow Row number to start reading from (0-indexed, usually 1 for skipping headers)
     * @return 2D Object array with test data
     * @throws IOException If file not found or cannot be read
     */
    public static Object[][] readExcelData(String filePath, String sheetName, int startRow) throws IOException {
        log.info("===== Loading test data from Excel file: {} =====", filePath);
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                log.error("❌ Sheet '{}' not found in Excel file", sheetName);
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in Excel file");
            }
            
            // Get last row number to determine number of test cases
            int lastRowNum = sheet.getLastRowNum();
            int numberOfTestCases = lastRowNum - startRow + 1;
            
            if (numberOfTestCases <= 0) {
                log.warn("⚠️ No test data found in sheet '{}'", sheetName);
                return new Object[0][0];
            }
            
            // Create 2D array for test data (3 columns: username, password, description)
            Object[][] data = new Object[numberOfTestCases][3];
            
            int dataIndex = 0;
            for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                
                if (row == null) {
                    log.debug("Skipping empty row: {}", rowNum);
                    continue;
                }
                
                // Read three columns: Username, Password, Description
                String username = getCellValueAsString(row, 0);
                String password = getCellValueAsString(row, 1);
                String description = getCellValueAsString(row, 2);
                
                data[dataIndex][0] = username;
                data[dataIndex][1] = password;
                data[dataIndex][2] = description;
                
                log.debug("Loaded row {}: Description='{}', Username='{}', Password='{}'", 
                    rowNum, description, username, password);
                
                dataIndex++;
            }
            
            log.info("✅ Excel file loaded successfully - {} test cases found", numberOfTestCases);
            return data;
            
        } catch (IOException e) {
            log.error("❌ Error reading Excel file: {}", filePath, e);
            throw new IOException("Failed to read Excel file: " + filePath, e);
        }
    }

    /**
     * Helper method to get cell value as String
     * Handles different cell types (String, Numeric, Boolean, etc.)
     * 
     * @param row The Excel row
     * @param cellIndex The column index (0-indexed)
     * @return Cell value as String, or empty string if cell is null
     */
    private static String getCellValueAsString(Row row, int cellIndex) {
        try {
            if (row.getCell(cellIndex) == null) {
                return "";
            }
            
            switch (row.getCell(cellIndex).getCellType()) {
                case STRING:
                    return row.getCell(cellIndex).getStringCellValue();
                case NUMERIC:
                    return String.valueOf((int) row.getCell(cellIndex).getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(row.getCell(cellIndex).getBooleanCellValue());
                default:
                    return "";
            }
        } catch (Exception e) {
            log.warn("Error reading cell at index {}: {}", cellIndex, e.getMessage());
            return "";
        }
    }

    /**
     * Convenience method with default sheet name "TestData"
     * 
     * @param filePath Path to the Excel file
     * @return 2D Object array with test data
     * @throws IOException If file not found or cannot be read
     */
    public static Object[][] readExcelData(String filePath) throws IOException {
        return readExcelData(filePath, "TestData", 1);
    }
}

