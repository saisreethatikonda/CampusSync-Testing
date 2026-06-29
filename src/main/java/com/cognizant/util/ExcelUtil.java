package com.cognizant.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil implements AutoCloseable {

    private Workbook workbook;
    private DataFormatter dataFormatter;

    public ExcelUtil(String filePath) {
        try {
            this.workbook = WorkbookFactory.create(new FileInputStream(filePath));
            this.dataFormatter = new DataFormatter();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to open Excel file: " + filePath, exception);
        }
    }

    public String getCellData(String sheetName, int rowNumber, int cellNumber) {
        Sheet sheet = getSheet(sheetName);
        Row row = sheet.getRow(rowNumber);

        if (row == null || row.getCell(cellNumber) == null) {
            return "";
        }

        return dataFormatter.formatCellValue(row.getCell(cellNumber));
    }

    public int getRowCount(String sheetName) {
        return getSheet(sheetName).getPhysicalNumberOfRows();
    }

    private Sheet getSheet(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        return sheet;
    }

    @Override
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Unable to close Excel workbook", exception);
        }
    }
}
