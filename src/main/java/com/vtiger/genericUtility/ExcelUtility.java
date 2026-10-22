package com.vtiger.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    private static final String LEADS_FILE =
            "./src/test/resources/VtigerTestData.xlsx";

    private static final String DASHBOARD_FILE =
            "./src/test/resources/VtigerTestDatadashboard.xlsx";

    // Existing method for Leads, Campaigns, and Trouble Tickets.
    public String getDataByTestId(
            String sheetName, String testId, String columnName)
            throws IOException {
        return readData(LEADS_FILE, sheetName, testId, columnName);
    }

    // Dashboard tests use the separate Dashboard workbook.
    public String getDashboardDataByTestId(
            String sheetName, String testId, String columnName)
            throws IOException {
        return readData(DASHBOARD_FILE, sheetName, testId, columnName);
    }

    private String readData(
            String filePath, String sheetName,
            String testId, String columnName) throws IOException {

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet not found: " + sheetName
                        + " in " + filePath);
            }

            DataFormatter formatter = new DataFormatter();
            Row header = sheet.getRow(0);
            if (header == null) {
                throw new IOException("Header row not found in sheet: "
                        + sheetName);
            }

            int idColumn = -1;
            int dataColumn = -1;

            for (int i = 0; i < header.getLastCellNum(); i++) {
                String headerName =
                        formatter.formatCellValue(header.getCell(i)).trim();

                // Leads workbook uses TC_ID; Dashboard workbook uses TestID.
                if (headerName.equalsIgnoreCase("TC_ID")
                        || headerName.equalsIgnoreCase("TestID")) {
                    idColumn = i;
                }

                if (headerName.equalsIgnoreCase(columnName)) {
                    dataColumn = i;
                }
            }

            if (idColumn == -1) {
                throw new IOException(
                        "TC_ID/TestID column not found in sheet: " + sheetName);
            }
            if (dataColumn == -1) {
                throw new IOException("Column not found: " + columnName
                        + " in sheet: " + sheetName);
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                String currentTestId =
                        formatter.formatCellValue(row.getCell(idColumn)).trim();

                if (currentTestId.equalsIgnoreCase(testId)) {
                    return formatter.formatCellValue(
                            row.getCell(dataColumn)).trim();
                }
            }

            throw new IOException("Test ID not found: " + testId
                    + " in sheet: " + sheetName);
        }
    }
}
