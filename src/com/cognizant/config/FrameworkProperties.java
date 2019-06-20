package com.cognizant.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FrameworkProperties {
	private static Properties properties;
	
	public static Properties getProperties() {
		return properties;
	}
	
	public static Properties getProperties(String workDirectory) {
		if (properties == null) {
			loadProperties(workDirectory);
			return properties;
		}
		else
			return getProperties();
	}
	
	public static void loadProperties(String workDirectory) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(workDirectory + System.getProperty("file.separator") + "Framework.Properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
