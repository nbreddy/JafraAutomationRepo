package com.cognizant.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.gargoylesoftware.htmlunit.javascript.host.InstallTrigger;

import org.testng.xml.XmlSuite.ParallelMode;

public class TestNGXMLGenerator {
	public static void runTestNGXML(ArrayList<MasterExecParam> testRunnerParameters, int threadCount, String logFilePath) {
		XmlSuite suite = new XmlSuite();
		suite.setName("Template Automation Suite");
		suite.setThreadCount(threadCount);
		suite.setParallel(ParallelMode.TESTS);

		HashMap<String, String> suiteParams = new HashMap<String,String>();
		suiteParams.put("LogFilePath", logFilePath);
		
		suite.setParameters(suiteParams);

		List<XmlTest> xmlTests = new ArrayList<XmlTest>();
		for (MasterExecParam param : testRunnerParameters) {


			HashMap<String, String> testngParams = new HashMap<String,String>();
			String scenario = param.getScenario();
			String testCase = param.getTestCase();
			String iteration = param.getIteration();
			String description = param.getDesc();
			testngParams.put("Scenario", scenario);
			testngParams.put("TestCase", testCase);
			testngParams.put("Iteration", iteration);
			testngParams.put("Description", description);

			int iter = Integer.parseInt(iteration);

			
			if(iter>1){
				for(int a=1; a<iter+1; a++){
					XmlTest test = new XmlTest(suite);
					test.setName("TestCase_" + testCase+"_Iteration_"+a+"");
					test.setParameters(testngParams);

					List<XmlClass> myClasses = new ArrayList<XmlClass>();
					myClasses.add(new XmlClass("com.cognizant.support.TestRunner"));
					test.setXmlClasses(myClasses);

					xmlTests.add(test);
				}
			}
			else{
				XmlTest test = new XmlTest(suite);
				test.setName("TestCase_" + testCase);
				test.setParameters(testngParams);

				List<XmlClass> myClasses = new ArrayList<XmlClass>();
				myClasses.add(new XmlClass("com.cognizant.support.TestRunner"));
				test.setXmlClasses(myClasses);

				xmlTests.add(test);
			}
		}
		suite.setTests(xmlTests);

		List<XmlSuite> testSuites = new ArrayList<XmlSuite>();
		testSuites.add(suite);

		TestNG testNG = new TestNG();
		testNG.setXmlSuites(testSuites);

		testNG.run();
	}

	//	public static void testNGGen(ArrayList<MasterExecParam> testRunnerParameters, int threadCount, String logFilePath){
	//		ArrayList<XmlSuite> suites = new ArrayList<XmlSuite>();
	//		
	//		XmlSuite suite = new XmlSuite();
	//		suite.setName("SuiteName");
	//		suite.setThreadCount(2);
	//		suite.setParallel(ParallelMode.TESTS);
	//		
	//		HashMap<String, String> suiteParam = new HashMap<String, String>();
	//		suiteParam.put("Log File", logFilePath);
	//		
	//		suite.setParameters(suiteParam);
	//		
	//		ArrayList<XmlTest> tests = new ArrayList<XmlTest>();
	//		for(MasterExecParam param:testRunnerParameters){
	//			HashMap<String, String> testParam = new HashMap<String, String>();
	//			testParam.put("TestCase", param.getTestCase());
	//			XmlTest test = new XmlTest();
	//			test.setName(param.getTestCase());
	//			test.setParameters(testParam);
	//			ArrayList<XmlClass> testClasses = new ArrayList<XmlClass>();
	//			XmlClass testClass = new XmlClass("com.cognizant.support.TestRunner");
	//			testClasses.add(testClass);
	//			test.setClasses(testClasses);
	//			tests.add(test);
	//		}
	//
	//		suite.setTests(tests);
	//		suites.add(suite);
	//		
	//		TestNG testNG = new TestNG();
	//		testNG.setXmlSuites(suites);
	//		testNG.run();
	//	}
}
