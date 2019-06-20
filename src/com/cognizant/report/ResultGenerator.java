package com.cognizant.report;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.config.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.cognizant.library.FunctionRepository;
import com.cognizant.util.AccessTestData;
import com.cognizant.support.TestRunner;


public class ResultGenerator {
	private AccessTestData dataTable;
	private String logFilePath;
	public static String logFileName;
	private int imgCounter;
	private ExtentReports extent;
	private Map<Integer, ExtentTest> testMap;
	public static StringBuilder htmlString = new StringBuilder();
	public static int passCount=0;
	public static int failCount=0;
	public static int i=0;
	public ResultGenerator(String logFilePath) {
		
		this.logFilePath = logFilePath;
		this.logFileName = logFilePath + "ExtentReport.html";
		
		extent = new ExtentReports(this.logFileName, true);
		extent.loadConfig(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "extent-config.xml"));
		String hostName = "Unknown";
		String hostIP = "0.0.0.0";
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			hostName = address.getHostName();
			hostIP = address.getHostAddress();
		} catch (UnknownHostException e) { }
		extent.addSystemInfo("Host Name", hostName)
			.addSystemInfo("Host IP", hostIP)
			.addSystemInfo("User Name", "Cognizant");
		
		testMap = new HashMap<Integer, ExtentTest>();
	}
	
	public void startTest(String testCase, String description) {
		ExtentTest test = extent.startTest(testCase, description);
		Integer threadID = (int) Thread.currentThread().getId();
		testMap.put(threadID, test);
	}
	
	private String captureScreenshot(WebDriver driver, ExtentTest test) {
		
		String tCName = TestRunner.tcName;
		
		String screenshotPath = logFilePath + FrameworkProperties.getProperties().getProperty("ScreenshotPath") + 
				System.getProperty("file.separator") + tCName+ 
//				System.getProperty("file.separator")+"Iterartion_"+TestRunner.iter+
				System.getProperty("file.separator") + tCName + "_Screen_" + FunctionRepository.imgCounter + ".jpg";
		File oDest = new File(screenshotPath);
		
		String screenRelativePath = "."+System.getProperty("file.separator") + FrameworkProperties.getProperties().getProperty("ScreenshotPath") + 
				System.getProperty("file.separator") + tCName+ 
//				System.getProperty("file.separator")+"Iterartion_"+TestRunner.iter+
				System.getProperty("file.separator") + tCName + "_Screen_" + FunctionRepository.imgCounter + ".jpg";
		FunctionRepository.imgCounter++;//Modified 3/26
		try {
			TakesScreenshot oScn = (TakesScreenshot) driver;
			File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(oScnShot, oDest);
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in capturing screenshot!");
		}
//		return screenshotPath;
		return screenRelativePath;
	}
	
	public void updateResult(WebDriver driver, Status status, String message) {
		ExtentTest test = testMap.get((int) Thread.currentThread().getId());
		switch (status) {
		case PASS:
			String takePassScreenshot = FrameworkProperties.getProperties().getProperty("TakePassScreenshot");
			if (takePassScreenshot.equalsIgnoreCase("Yes"))
				test.log(LogStatus.PASS, message + " " + test.addScreenCapture(captureScreenshot(driver, test)));
			else
				test.log(LogStatus.PASS, message);
			break;
		case FAIL:
			FunctionRepository.testCaseFailStatus=true; //Modified 3/26
			test.log(LogStatus.FAIL, message + " " + test.addScreenCapture(captureScreenshot(driver, test)));
			break;
		case WARNING:
			test.log(LogStatus.WARNING, message + " " + test.addScreenCapture(captureScreenshot(driver, test)));
			break;
		case ERROR:
			test.log(LogStatus.ERROR, message + " " + test.addScreenCapture(captureScreenshot(driver, test)));
			break;
		case INFO:
		default:
			test.log(LogStatus.INFO, message + " " + test.addScreenCapture(captureScreenshot(driver, test)));
			break;
		}
	}
	
	public void updateTestCaseStatus(String description) throws InvalidFormatException, IOException{
		DateFormat dateTimeformat = new SimpleDateFormat("MM/dd/YYYY - hh:mm:ss aa");
		dateTimeformat.setTimeZone(TimeZone.getTimeZone("PST"));
		Date date = new Date();
		String dateTime = dateTimeformat.format(date);
		this.htmlString.append("<tr>");
		this.htmlString.append("<td align=\"center\"> "+TestRunner.tcName+" </td>");
		this.htmlString.append("<td> "+description+" </td>");
		
		
		if(FunctionRepository.testCaseFailStatus){
			this.htmlString.append("<td align=\"center\"><font color=\"ff0000\"> Fail </td>");
			this.failCount++;
			
		}
		else{
			this.htmlString.append("<td align=\"center\"><font color=\"#50864D\"> Pass </td>");
			this.passCount++;
		}
		this.htmlString.append("<td align=\"center\">"+dateTime+"</td>");
		this.htmlString.append("</tr>");
		
	}
	
	
	public void closeTest(String description) throws IOException, InvalidFormatException {
		updateTestCaseStatus(description);
		extent.endTest(testMap.get((int) Thread.currentThread().getId()));
	}
	
	public void closeResult() throws IOException, EmailException {
		
		extent.flush();
		extent.close();
		CreateHtmlResult.createHtmlResult(logFilePath+"TestSuiteReport.html");
		/*File file = new File(logFileName);
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
