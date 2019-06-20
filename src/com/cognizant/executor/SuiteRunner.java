package com.cognizant.executor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.support.MasterExecParam;
import com.cognizant.support.TestNGXMLGenerator;
import com.cognizant.util.AccessTestRunner;

public class SuiteRunner {
	
	public void verifyInvoke(){
		System.out.println("Invoke verified");
	}
	
	
	public static void main(String[] args) {
		
//		String currentScenario = "Scenario";
		ArrayList<MasterExecParam> testRunnerParameters;
		
		String suiteFilePath = System.getProperty("user.dir") + System.getProperty("file.separator");
		Properties properties = FrameworkProperties.getProperties(suiteFilePath);
		String suiteFileName = properties.getProperty("SuiteExecutionData") + ".xlsx";
		String suiteExecutionSheet = properties.getProperty("SuiteExecutionSheet");
		String miscParameterSheet = properties.getProperty("MiscParameterSheet");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		String logFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + properties.getProperty("ResultPath") 
				+ System.getProperty("file.separator") + "Execution " + dateFormat.format(new Date()) + System.getProperty("file.separator");
		
		testRunnerParameters = AccessTestRunner.fetchMasterData(suiteFilePath, suiteFileName, suiteExecutionSheet);
		
			
		String strMultiThread = AccessTestRunner.fetchMiscData(suiteFilePath, suiteFileName, miscParameterSheet, "Multi-Thread").trim();
		int threadCount = 1;
		if (strMultiThread.equalsIgnoreCase("YES"))	
			threadCount = Integer.parseInt(AccessTestRunner.fetchMiscData(suiteFilePath, suiteFileName, miscParameterSheet, "ThreadCount").trim());
		
		TestNGXMLGenerator.runTestNGXML(testRunnerParameters, threadCount, logFilePath);
//		for (MasterExecParam param : testRunnerParameters) {
//			currentScenario = param.getScenario().trim();
//			String dataFilePath = suiteFilePath + properties.getProperty("TestDataPath") + System.getProperty("file.separator");
//			String dataFileName = currentScenario + ".xlsx";
//			ScriptDriver scriptDriver = new ScriptDriver(param, dataFilePath, dataFileName, logFilePath);
//			scriptDriver.driveTestExecution();
//		}
	}
}
