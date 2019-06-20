package com.cognizant.support;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.config.Status;
import com.cognizant.executor.SuiteRunner;
import com.cognizant.library.FunctionRepository;
import com.cognizant.report.ResultGenerator;
import com.cognizant.util.AccessTestData;
import com.cognizant.util.WebDriverUtil;

public class DriverScript {
	private WebDriver driver;
	private ScriptHelper scriptHelper;
	private MasterExecParam suiteRunParam;
	private String dataFilePath;
	private String dataFileName;
	private ResultGenerator result;
	private AccessTestData dataTable;
	private List<String> businessFlow;
	private boolean isWrappedUp;
	private String environment;
	public static String env;
	
	/**
	 * @param suiteRunParam
	 * @param dataFilePath
	 * @param dataFileName
	 * @param isWrappedUp
	 * @param result
	 */
	public DriverScript(MasterExecParam suiteRunParam, String dataFilePath, String dataFileName, ResultGenerator result, String environment) {
		this.suiteRunParam = suiteRunParam;
		this.dataFilePath = dataFilePath;
		this.dataFileName = dataFileName;
		this.environment = environment;
		this.isWrappedUp = false;
		this.result = result;
	}
	
	public void driveTestExecution() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		initializeDriver();
		initializeDataTable();
		initializeScript();
		executeBusinessFlow();
		wrapUp();
	}
	
	public void initializeDriver() {
		String browserDriverPath = FrameworkProperties.getProperties().getProperty("BrowserDriverPath") + System.getProperty("file.separator");
		driver = WebDriverUtil.getDriver(browserDriverPath, suiteRunParam);
		//driver.manage().window().maximize();
	}
	
	public void initializeDataTable() {
		dataTable = new AccessTestData(dataFilePath, dataFileName, suiteRunParam.getTestCase(), driver, result);
		this.env = dataTable.getData("TestData", "AUT");
	}
	
	public void initializeScript() {
		scriptHelper = new ScriptHelper(driver, dataTable, result);
		businessFlow = getBusinessFlow();
	}
	
	public List<String> getBusinessFlow() {
		ExcelOperations functionData = new ExcelOperations(dataFilePath, dataFileName);
		functionData.setSheetName(FrameworkProperties.getProperties().getProperty("BusinessFlowSheet"));
		
		int rowNum = functionData.getRowNum(suiteRunParam.getTestCase(), 0);
		List<String> businessFlow = new ArrayList<String>();
		if (rowNum != -1) {
			int currentCol = 1;
			while (true) {
				String function = functionData.getValue(rowNum, currentCol);
				if (function.equals("") || function.isEmpty())
					break;
				businessFlow.add(function);
				currentCol++;
			}
		} else {
			try {
				throw new Exception("Test Case " + suiteRunParam.getTestCase() + " not found in the data sheet!");
			} catch (Exception e) {
				exceptionHandler(result, e);
			}
		}
		return businessFlow;
	}
	
	public void executeBusinessFlow() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int i = 0; i < businessFlow.size(); i++)
			if (!isWrappedUp)
				invokeKeyword(businessFlow.get(i));
	}
	
	
	
	public void invokeKeyword(String currentKeyword) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		
		Boolean isMethodFound = false;
		final String CLASS_FILE_EXTENSION = ".class";
		File packageDirectory = new File(System.getProperty("user.dir")	+ System.getProperty("file.separator") + "bin" 
				+ System.getProperty("file.separator") + "com" + System.getProperty("file.separator") + "cognizant" 
				+ System.getProperty("file.separator") + "library");
		File[] packageFiles = packageDirectory.listFiles();
		String packageName = "com.cognizant." + packageDirectory.getName();
		
		for (int i = 0; i < packageFiles.length; i++) {
			File packageFile = packageFiles[i];
			String fileName = packageFile.getName();
			
			if (fileName.endsWith(CLASS_FILE_EXTENSION)) {
				String className = fileName.substring(0, fileName.length() - CLASS_FILE_EXTENSION.length());
				Method executeComponent;
				Class<?> reusableComponents;
				
				try {
					reusableComponents = Class.forName(packageName + "." + className);
					
					try {
						executeComponent = reusableComponents.getMethod(currentKeyword, (Class<?>[]) null);
					} catch(NoSuchMethodException ex) {
						continue;
					}
					
					isMethodFound = true;
					Constructor<?> constructor = reusableComponents.getDeclaredConstructors()[0];
					Object businessComponent = constructor.newInstance(scriptHelper);
					executeComponent.invoke(businessComponent, (Object[]) null);
					break;
				} catch (Exception e) {
					exceptionHandler(result, e);
				}
			}
		}
		
		if (!isMethodFound) {
			try {
				throw new Exception("Business Keyword " + currentKeyword + "() not found!");
			} catch (Exception e) {
				exceptionHandler(result, e);
			}
		}
	}
	
	public void wrapUp() {
		if (!isWrappedUp) {
			driver.quit();
			isWrappedUp = true;
		}
	}
	
	
	
	public void exceptionHandler(ResultGenerator result, Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		String stackTrace = stringWriter.toString();
		result.updateResult(driver, Status.FAIL, stackTrace);
		wrapUp();
	}
}
