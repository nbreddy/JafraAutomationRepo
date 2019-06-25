package com.cognizant.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.config.Browser;
import com.cognizant.config.DeviceType;
import com.cognizant.support.MasterExecParam;

public class WebDriverUtil {
	public static WebDriver getDriver(String browserDriverPath, MasterExecParam suiteRunParam) {
		WebDriver driver;
		
		
		DeviceType deviceType = suiteRunParam.getDeviceType();
		switch (deviceType) {
		case LOCAL:
		default:
			Browser browser = suiteRunParam.getBrowser();
			switch (browser) {
			case INTERNETEXPLORER:
				File ieDriverFile = new File(browserDriverPath + "IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", ieDriverFile.getAbsolutePath());
				driver = new InternetExplorerDriver();
				break;
			case FIREFOX:
				File firefoxDriverFile = new File(browserDriverPath + "geckodriver");
				System.setProperty("webdriver.gecko.driver", firefoxDriverFile.getAbsolutePath());
				driver = new FirefoxDriver();
				break;
			case CHROME:
			default:
				DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				File chromeDriverFile = new File(browserDriverPath + "chromedriver");
				System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
				
				/*try {
					Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
						
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized","ignore-certificate-errors");
				
				driver = new ChromeDriver(options);
				
			
				
				break;
			}
			break;
			
		case LOCAL_EMULATED_DEVICE:
			String deviceName = suiteRunParam.getDeviceName();
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", deviceName);
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			File chromeDriverFile = new File(browserDriverPath + "chromedriver");
			System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
			driver = new ChromeDriver(desiredCapabilities);
			break;
		}
		return driver;
	}
	
	public static void waitDriver(WebDriver driver, long timeoutInSeconds) {
		try {
			Thread.sleep(timeoutInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void waitUntilElementLocated(WebDriver driver, By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public static void waitUntilElementVisible(WebDriver driver, By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void waitUntilElementEnabled(WebDriver driver, By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
	}
	
	
	
	public static boolean exists(WebDriver driver, By by) {
		boolean isPresent;
		try {
			isPresent = driver.findElement(by).isDisplayed();
			//isPresent = true;
		} catch (Exception e) {
			isPresent = false;
		}
		return isPresent;
	}
}
