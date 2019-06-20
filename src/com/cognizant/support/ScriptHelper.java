package com.cognizant.support;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.report.ResultGenerator;
import com.cognizant.util.AccessTestData;

public class ScriptHelper {
	private final WebDriver driver;
	private final Properties properties;
	private final AccessTestData dataTable;
	private final ResultGenerator result;
	
	/**
	 * @param driver
	 */
	public ScriptHelper(WebDriver driver, AccessTestData dataTable, ResultGenerator result) {
		super();
		this.driver = driver;
		this.properties = FrameworkProperties.getProperties();
		this.dataTable = dataTable;
		this.result = result;
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @return the dataTable
	 */
	public AccessTestData getDataTable() {
		return dataTable;
	}

	/**
	 * @return the result
	 */
	public ResultGenerator getResult() {
		return result;
	}
}
