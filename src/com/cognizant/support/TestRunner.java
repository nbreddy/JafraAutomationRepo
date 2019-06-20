package com.cognizant.support;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.report.ResultGenerator;
import com.cognizant.support.MasterExecParam;
import com.cognizant.util.AccessTestRunner;

public class TestRunner {
	private String currentScenario;
	private MasterExecParam testRunnerParameters;
	private String suiteFilePath;
	private Properties properties;
	private String suiteFileName;
	private String suiteExecutionSheet;
	private static ResultGenerator result;
	private String iteration;
	public static int iter;
	public static String tcName ="";
	
	@BeforeSuite
	@Parameters("LogFilePath")
	public void beforeSuite(String logFilePath) {
		result = new ResultGenerator(logFilePath);
	}
	
	@Test
	@Parameters("TestCase")
	public synchronized void test(String testCase) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		currentScenario = testRunnerParameters.getScenario().trim();
		iteration = testRunnerParameters.getIteration().trim();
		String dataFilePath = suiteFilePath + properties.getProperty("TestDataPath") + System.getProperty("file.separator");
		String dataFileName = currentScenario + ".xlsx";
		DriverScript driverScript = new DriverScript(testRunnerParameters, dataFilePath, dataFileName, result, iteration);
//		int iter=1;
//		if(!iteration.equalsIgnoreCase(""))
//			iter = Integer.parseInt(iteration);
//		for(int i=1; i<iter+1; i++){
//			this.iter = i;
			driverScript.driveTestExecution();
//		}
	}
	
	@BeforeTest
	@Parameters({"TestCase"})
	public synchronized void beforeTest(String testCase, ITestContext testContext) {
		suiteFilePath = System.getProperty("user.dir") + System.getProperty("file.separator");
		properties = FrameworkProperties.getProperties(suiteFilePath);
		suiteFileName = properties.getProperty("SuiteExecutionData") + ".xlsx";
		suiteExecutionSheet = properties.getProperty("SuiteExecutionSheet");
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//		logFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + properties.getProperty("ResultPath") +
//				System.getProperty("file.separator") + "Execution " + dateFormat.format(new Date()) + System.getProperty("file.separator");
		
		testRunnerParameters = AccessTestRunner.fetchMasterData(suiteFilePath, suiteFileName, suiteExecutionSheet, testCase);
		
		String scenario = testRunnerParameters.getScenario();
//		ITestContext obj = null;
		String tCName = testContext.getCurrentXmlTest().getName();
		this.tcName = tCName;
//		result.startTest(tCName+" ->\n"+testRunnerParameters.getDesc(), "");
		result.startTest(tCName, testRunnerParameters.getDesc());
	}

	@AfterTest
	@Parameters({"Description"})
	public synchronized void afterTest(String description) throws InvalidFormatException, IOException {
		result.closeTest(description);
	}
	
	@AfterSuite
	public void afterSuite() throws IOException, EmailException {
		result.closeResult();
	}
}