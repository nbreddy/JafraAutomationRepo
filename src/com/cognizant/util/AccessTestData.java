package com.cognizant.util;

import org.openqa.selenium.WebDriver;

import com.cognizant.config.Status;
import com.cognizant.report.ResultGenerator;
import com.cognizant.support.ExcelOperations;

public class AccessTestData {
	private String dataFilePath;
	private String dataFileName;
	private String currentTestCase;
	private WebDriver driver;
	private ResultGenerator result;
	
	/**
	 * @param dataFilePath
	 * @param dataFileName
	 * @param currentTestCase
	 * @param driver
	 * @param result
	 */
	public AccessTestData(String dataFilePath, String dataFileName, String currentTestCase, WebDriver driver, ResultGenerator result) {
		this.dataFilePath = dataFilePath;
		this.dataFileName = dataFileName;
		this.currentTestCase = currentTestCase;
		this.driver = driver;
		this.result = result;
	}
	
	public String getData(String sheetName, String fieldName) {
		ExcelOperations testData = new ExcelOperations(dataFilePath, dataFileName);
		testData.setSheetName(sheetName);
		String data = null;
		
		// Skip row 0 or the Header row
		int rowNum = testData.getRowNum(currentTestCase, 0, 1);
		if (rowNum != -1) {
			// Skip column 0
			data = testData.getValue(rowNum, fieldName);
		} else
			result.updateResult(driver, Status.FAIL, currentTestCase + " is not present in the data sheet");
		return data;
	}
	
	public void putData(String sheetName, String fieldName, String data) {
		ExcelOperations testData = new ExcelOperations(dataFilePath, dataFileName);
		testData.setSheetName(sheetName);
		
		// Skip row 0 or the Header row
		int rowNum = testData.getRowNum(currentTestCase, 0, 1);
		if (rowNum != -1) {
			// Skip column 0
			testData.setValue(rowNum, fieldName, data);
		} else 
			result.updateResult(driver, Status.FAIL, currentTestCase + " is not present in the data sheet");
	}
	
	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName + ".xlsx";
	}
	
	public String getDataFileName() {
		return dataFileName.substring(0, dataFileName.indexOf(".xlsx"));
	}
	
	public void setCurrentTestCase(String scenario, String currentTestCase) {
		this.currentTestCase = scenario + "_" + currentTestCase;
	}
	
	public String getCurrentTestCase() {
		return currentTestCase;
	}
}
