package com.cognizant.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.cognizant.config.Browser;
import com.cognizant.config.DeviceType;
import com.cognizant.support.MasterExecParam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AccessTestRunner {
	public static ArrayList<MasterExecParam> fetchMasterData(String suiteFilePath, String suiteFileName, String suiteSheetName) {
		String scenario = "Scenario";
		String testCase = "TestCase";
		String desc = "Description";
		
		DeviceType deviceType = null;
		Browser browser = null;
		String deviceName = "Emulator";
		String envName = ""; 
		
		ArrayList<MasterExecParam> masterExecData = new ArrayList<MasterExecParam>();
		try {
			FileInputStream dataFile = new FileInputStream(new File(suiteFilePath + suiteFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = workbook.getSheet(suiteSheetName);
			
						
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				boolean exec = false;
				Row row = rowIterator.next();
				if (row.getRowNum() == 0)
					continue;
				
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 0)
						scenario = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 1)
						testCase = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 2)
						desc = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 3)
						exec = cell.getStringCellValue().equalsIgnoreCase("Yes");
					else if (cell.getColumnIndex() == 4) {
						if (cell.getStringCellValue().equalsIgnoreCase("LOCAL"))
							deviceType = DeviceType.LOCAL;
						else if (cell.getStringCellValue().equalsIgnoreCase("REMOTE"))
							deviceType = DeviceType.REMOTE;
						else if (cell.getStringCellValue().equalsIgnoreCase("LOCAL_EMULATED_DEVICE"))
							deviceType = DeviceType.LOCAL_EMULATED_DEVICE;
						else if (cell.getStringCellValue().equalsIgnoreCase("REMOTE_EMULATED_DEVICE"))
							deviceType = DeviceType.REMOTE_EMULATED_DEVICE;
					}
					else if (cell.getColumnIndex() == 5) {
						if (cell.getStringCellValue().equalsIgnoreCase("InternetExplorer"))
							browser = Browser.INTERNETEXPLORER;
						else if (cell.getStringCellValue().equalsIgnoreCase("Firefox"))
							browser = Browser.FIREFOX;
						else if (cell.getStringCellValue().equalsIgnoreCase("Chrome"))
							browser = Browser.CHROME;
					}
					else if (cell.getColumnIndex() == 6)
						deviceName = cell.getStringCellValue();
					
					else if (cell.getColumnIndex() == 7)
						envName = cell.getStringCellValue();
					
					
					else
						;
				}
				if (exec)
					masterExecData.add(new MasterExecParam(scenario, testCase, desc, exec, deviceType, browser, deviceName, envName));
			}
			workbook.close();
			dataFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return masterExecData;
	}
	
	public static MasterExecParam fetchMasterData(String suiteFilePath, String suiteFileName, String suiteSheetName, String testCaseID) {
		String scenario = "Scenario";
		String testCase = "TestCase";
		String desc = "Description";
		DeviceType deviceType = null;
		boolean exec = false;
		Browser browser = null;
		String deviceName = "Emulator";
		String envName = ""; 
		
		boolean isTCFound = false;
		MasterExecParam masterExecData = null;
		try {
			FileInputStream dataFile = new FileInputStream(new File(suiteFilePath + suiteFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = workbook.getSheet(suiteSheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0)
					continue;
				
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 0)
						scenario = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 1) {
						testCase = cell.getStringCellValue();
						if (!testCase.equalsIgnoreCase(testCaseID))
							break;
						else
							isTCFound = true;
					}
					else if (cell.getColumnIndex() == 2)
						desc = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 3)
						exec = cell.getStringCellValue().equalsIgnoreCase("Yes");
					else if (cell.getColumnIndex() == 4) {
						if (cell.getStringCellValue().equalsIgnoreCase("LOCAL"))
							deviceType = DeviceType.LOCAL;
						else if (cell.getStringCellValue().equalsIgnoreCase("REMOTE"))
							deviceType = DeviceType.REMOTE;
						else if (cell.getStringCellValue().equalsIgnoreCase("LOCAL_EMULATED_DEVICE"))
							deviceType = DeviceType.LOCAL_EMULATED_DEVICE;
						else if (cell.getStringCellValue().equalsIgnoreCase("REMOTE_EMULATED_DEVICE"))
							deviceType = DeviceType.REMOTE_EMULATED_DEVICE;
					}
					else if (cell.getColumnIndex() == 5) {
						if (cell.getStringCellValue().equalsIgnoreCase("InternetExplorer"))
							browser = Browser.INTERNETEXPLORER;
						else if (cell.getStringCellValue().equalsIgnoreCase("Firefox"))
							browser = Browser.FIREFOX;
						else if (cell.getStringCellValue().equalsIgnoreCase("Chrome"))
							browser = Browser.CHROME;
					}
					else if (cell.getColumnIndex() == 6)
						deviceName = cell.getStringCellValue();
					else if (cell.getColumnIndex() == 7)
						envName = cell.getStringCellValue();
					else
						;
				}
				if (isTCFound && exec) {
					masterExecData = new MasterExecParam(scenario, testCase, desc, exec, deviceType, browser, deviceName, envName);
					break;
				}
			}
			workbook.close();
			dataFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return masterExecData;
	}

	public static String fetchMiscData(String suiteFilePath, String suiteFileName, String paramSheetName, String field) {
		String strVal = null;
		try {
			FileInputStream dataFile = new FileInputStream(new File(suiteFilePath + suiteFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = workbook.getSheet(paramSheetName);
			
			int colNum = -1;
			XSSFRow firstRow = sheet.getRow(0);
			for (int curColNum = 0; curColNum < firstRow.getLastCellNum(); curColNum++) {
				XSSFCell cell = firstRow.getCell(curColNum);
				if (cell.getStringCellValue().trim().equalsIgnoreCase(field)) {
					colNum = curColNum;
					break;
				}
			}
			
			XSSFRow row = sheet.getRow(1);		// Retrieve the data from first row only
			XSSFCell cell = row.getCell(colNum);
			strVal = cell.getStringCellValue();
			
			workbook.close();
			dataFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strVal;
	}
}
