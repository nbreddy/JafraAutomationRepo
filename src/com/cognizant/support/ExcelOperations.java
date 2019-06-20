package com.cognizant.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	private String filePath;
	private String fileName;
	private String sheetName;
	
	/**
	 * @param filePath
	 * @param fileName
	 */
	public ExcelOperations(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
		sheetName = "";
	}

	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	
	

	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	private XSSFWorkbook openFileForReading() {
//		String absoluteFilePath = filePath + Util.getFileSeparator() + fileName;
		String absoluteFilePath = filePath + System.getProperty("file.separator") + fileName;
		XSSFWorkbook workbook = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(absoluteFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	private void writeIntoFile(XSSFWorkbook workbook) {
//		String absoluteFilePath = filePath + Util.getFileSeparator() + fileName;
		String absoluteFilePath = filePath + System.getProperty("file.separator") + fileName;

		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(absoluteFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private XSSFSheet getWorkSheet(XSSFWorkbook workbook) {
		return workbook.getSheet(sheetName);
	}

	public int getRowNum(String key, int columnNum, int startRowNum) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		String currentValue;
		for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); currentRowNum++) {
			XSSFRow row = worksheet.getRow(currentRowNum);
			XSSFCell cell = row.getCell(columnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(key))
				return currentRowNum;
		}
		return -1;
	}

	private String getCellValueAsString(XSSFCell cell, FormulaEvaluator formulaEvaluator) {
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
	}

	public int getRowNum(String key, int columnNum) {
		return getRowNum(key, columnNum, 0);
	}

	public int getLastRowNum() {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		return worksheet.getLastRowNum();
	}

	public int getRowCount(String key, int columnNum, int startRowNum) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		int rowCount = 0;
		boolean keyFound = false;

		String currentValue;
		for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); currentRowNum++) {
			XSSFRow row = worksheet.getRow(currentRowNum);
			XSSFCell cell = row.getCell(columnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(key)) {
				rowCount++;
				keyFound = true;
			} else {
				if (keyFound) {
					break; // Assumption: Keys always appear contiguously
				}
			}
		}
		return rowCount;
	}

	public int getRowCount(String key, int columnNum) {
		return getRowCount(key, columnNum, 0);
	}

	public int getColumnNum(String key, int rowNum) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow row = worksheet.getRow(rowNum);
		String currentValue;
		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {
			XSSFCell cell = row.getCell(currentColumnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(key)) {
				return currentColumnNum;
			}
		}
		return -1;
	}

	public String getValue(int rowNum, int columnNum) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow row = worksheet.getRow(rowNum);
		XSSFCell cell = row.getCell(columnNum);
		
		
		
		return getCellValueAsString(cell, formulaEvaluator);
	}

	public String getValue(int rowNum, String columnHeader) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		XSSFRow row = worksheet.getRow(0); // 0 because header is always in the
											// first row
		int columnNum = -1;
		String currentValue;
		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {

			XSSFCell cell = row.getCell(currentColumnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);
			
			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		row = worksheet.getRow(rowNum);
		XSSFCell cell = row.getCell(columnNum);
		return getCellValueAsString(cell, formulaEvaluator);
	}
	
	public void setValue(int rowNum, int columnNum, String value) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);

		XSSFRow row = worksheet.getRow(rowNum);
		XSSFCell cell = row.createCell(columnNum);
		cell.setCellValue(value);
		writeIntoFile(workbook);
	}

	public void setValue(int rowNum, String columnHeader, String value) {
		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow row = worksheet.getRow(0); // 0 because header is always in the
											// first row
		int columnNum = -1;
		String currentValue;
		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {

			XSSFCell cell = row.getCell(currentColumnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		row = worksheet.getRow(rowNum);
		XSSFCell cell = row.createCell(columnNum);
		cell.setCellValue(value);
		writeIntoFile(workbook);
	}
}
