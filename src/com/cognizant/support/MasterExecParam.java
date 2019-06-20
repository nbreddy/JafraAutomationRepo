package com.cognizant.support;

import com.cognizant.config.Browser;
import com.cognizant.config.DeviceType;

public class MasterExecParam {
	private String scenario;
	private String testCase;
	private String desc;
	private boolean exec;
	private DeviceType deviceType;
	private Browser browser;
	private String deviceName;
	private String iteration;
	
	/**
	 * @param scenario
	 * @param testCase
	 * @param desc
	 * @param exec
	 * @param deviceType
	 * @param browser
	 * @param deviceName
	 */
	public MasterExecParam(String scenario, String testCase, String desc, boolean exec, DeviceType deviceType, Browser browser, String deviceName, String iteration) {
		this.scenario = scenario;
		this.testCase = testCase;
		this.desc = desc;
		this.exec = exec;
		this.deviceType = deviceType;
		this.browser = browser;
		this.deviceName = deviceName;
		this.iteration = iteration;
	}

	/**
	 * @return the scenario
	 */
	public String getScenario() {
		return scenario;
	}

	/**
	 * @param scenario the scenario to set
	 */
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	/**
	 * @return the testCase
	 */
	public String getTestCase() {
		return testCase;
	}

	/**
	 * @param testCase the testCase to set
	 */
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the exec
	 */
	public boolean isExec() {
		return exec;
	}

	/**
	 * @param exec the exec to set
	 */
	public void setExec(boolean exec) {
		this.exec = exec;
	}
	
	/**
	 * @return the deviceType
	 */
	public DeviceType getDeviceType() {
		return deviceType;
	}

	/**
	 * @param exec the deviceType to set
	 */
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param exec the deviceNamee to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getIteration() {
		return iteration;
	}
	public void setIteration(String iteration) {
		this.iteration = iteration;
	}
}
