package com.cognizant.library;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.config.Status;
import com.cognizant.pom.EcomPages.GlobalObjects;
import com.cognizant.pom.EcomPages.JoinJafraConsultantPage;
import com.cognizant.pom.EcomPages.PaymentInfoPage;
import com.cognizant.report.ResultGenerator;
import com.cognizant.support.ScriptHelper;
import com.cognizant.util.AccessTestData;

public class CommonFunctions  {
	private WebDriver driver;
	private AccessTestData dataTable;
	private ResultGenerator result;
	private String defaultDataSheet;

	public CommonFunctions(ScriptHelper scriptHelper) {
		this.driver = scriptHelper.getDriver();
		this.dataTable = scriptHelper.getDataTable();
		this.result = scriptHelper.getResult();
		defaultDataSheet = FrameworkProperties.getProperties().getProperty("TestDataSheet");


	}

	//	public boolean clickObject(By obj) {
	//		boolean blnClicked = false;
	//		try {
	//			JavascriptExecutor js = (JavascriptExecutor) driver;
	//			if (isElementDisplayed( obj, 60)) {
	//				driver.findElement(obj).click();
	//			}
	//			else
	//			{
	//				js.executeScript("arguments[0].click();", driver.findElement(obj));
	//			}
	//			blnClicked = true;
	//
	//		} 
	//
	//		catch (Exception e) {
	//			System.out.println("Unknown Exception");
	//			if (isElementClickable(obj, 60)) {
	//				driver.findElement(obj).click();
	//				driver.findElement(obj);
	//				blnClicked = true;
	//			}
	//		}
	//
	//		return blnClicked;
	//	}
	
	public int clickObject(By object, int index) throws InterruptedException{
		boolean flag = false;
		List<WebElement> elements = null;
		isElementDisplayed(object, 15);
		elements = driver.findElements(object);
		for(int i=index; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
				index=i;
				flag=true;
				break;
			}
		}
		if(!flag){
			Thread.sleep(5000);
			for(int i=index; i<elements.size(); i++){
				if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
					index=i;
					flag=true;
					break;
				}
			}
		}
		elements.get(index).click();
		return index;
	}

	public void clickObject(By object) throws InterruptedException{
		int index = 0;
		boolean flag = false;
		List<WebElement> elements = null;
		isElementDisplayed(object, 15);
		elements = driver.findElements(object);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
				index=i;
				flag=true;
				break;
			}
		}
		if(!flag){
			Thread.sleep(5000);
			for(int i=0; i<elements.size(); i++){
				if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
					index=i;
					flag=true;
					break;
				}
			}
		}
		try{
		elements.get(index).click();
		}
		catch(Exception e){
			isPageLoadingSuccessfull();
			elements.get(index).click();
		}
	}
	
	public void submitObject(By object) throws InterruptedException{
		int index = 0;
		boolean flag = false;
		List<WebElement> elements = null;
		isElementDisplayed(object, 15);
		elements = driver.findElements(object);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
				index=i;
				flag=true;
				break;
			}
		}
		if(!flag){
			Thread.sleep(5000);
			for(int i=0; i<elements.size(); i++){
				if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
					index=i;
					flag=true;
					break;
				}
			}
		}
		try{
		elements.get(index).submit();;
		}
		catch(Exception e){
			isPageLoadingSuccessfull();
			elements.get(index).submit();;
		}
	}


	public void clickVisibleObject(By object1, By object2) throws InterruptedException{
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i=0; i<20; i++){
			if(isElementDisplayed(object1, 1)){
				flag1=true;
				break;
			}
			if(isElementDisplayed(object2,1)){
				flag2=true;
				break;
			}


		}
		if(flag1){
			scrollToElement(object1);
			clickObject(object1);
		}
		if(flag2){
			scrollToElement(object2);
			clickObject(object2);
		}
	}


	//	public boolean isElementDisplayed( By obj, int timeout) {
	//		boolean IsDisplayed = false;
	//		boolean IsExists;
	//		try {
	//
	//			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	//			WebDriverWait wait = new WebDriverWait(driver, timeout);
	//			wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
	//			IsDisplayed = driver.findElement(obj).isDisplayed();
	//			
	//			
	//			
	////			area.height = elements.get(0).getSize();
	//			
	//			
	//
	//			//				String s= driver.findElement(obj).getText();
	//			//				System.out.println(s);
	//
	//		} catch (StaleElementReferenceException stle) {
	//			//currentTime();
	//			// driver.navigate().refresh();
	//			JavascriptExecutor js = (JavascriptExecutor) driver;
	//			js.executeScript("window.stop()");
	//			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	//			WebDriverWait wait = new WebDriverWait(driver, 10);
	//			wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
	//			IsDisplayed = driver.findElement(obj).isDisplayed();
	//			System.out.println("Element Displayed After Stale");
	//		} catch (Exception e) {
	//			IsDisplayed = false;
	//		}
	//
	//
	//		return IsDisplayed;
	//
	//	}

	public boolean isElementDisplayed(By object, int timeOut) throws InterruptedException{
		List<WebElement> elements = null;
		for(int i=0; i<timeOut; i++){
			try{
				elements = driver.findElements(object);
				if(!elements.isEmpty())
					break;
				else
					Thread.sleep(1000);
			}
			catch(Exception e){
				Thread.sleep(1000);
			}
		}
		boolean isDisplayed = false;
		try{
			for(int i=0; i<elements.size(); i++){
				if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
					isDisplayed=true;
					break;
				}
			}
		}catch(Exception e){
			System.out.println("object not in page");
		}
		return isDisplayed;
	}

	public void verifyElementExistance(String objName, By obj, int timeout) throws InterruptedException{

		if(isElementDisplayed(obj, timeout))
			result.updateResult(driver, Status.PASS, objName+" is displayed");
		else
			result.updateResult(driver, Status.FAIL, objName+" is not displayed");
	}



	public boolean isElementClickable( By obj, int timeout) {
		boolean IsDisplayed = false;
		try {
			//currentTime();
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(obj));
			IsDisplayed = driver.findElement(obj).isDisplayed();
			System.out.println("Element Displayed");

		} catch (StaleElementReferenceException stle) {
			//currentTime();
			// driver.navigate().refresh();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.stop()");
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(obj));
			IsDisplayed = driver.findElement(obj).isDisplayed();
			System.out.println("Element Displayed After Stale");
		} catch (Exception e) {
			IsDisplayed = false;
		}

		return IsDisplayed;

	}

	public String getObjText (By obj)
	{
		String sValue="";
		List<WebElement> elements = null;
		try
		{
			if (isElementDisplayed(obj, 60))
			{
				elements = driver.findElements(obj);
				for(int i=0; i<elements.size(); i++){
					if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
						sValue = elements.get(i).getText();
					}
				}


			}
		}
		catch(Exception e)
		{
			System.out.println(e);

		}


		return sValue.trim();
	}
	
	

	public void waitforProcessing() throws Exception {
		int iWaitTime=1;

		while(isElementDisplayed(By.xpath("//div[@class='loading-spinner']"), 1) && iWaitTime<=60)
			iWaitTime=iWaitTime+1;

		Thread.sleep(3000);
		//while(driver.findElement(By.xpath("//div[@class='loading-spinner']")).isDisplayed() && iWaitTime<=60){

		/*//Thread.sleep(1000);
			//			String objstyle = driver.findElement(By.xpath("//div[@class='loading-spinner']")).getAttribute("style");
			//			System.out.println(objstyle);


			//System.out.println("Waiting.. "+ iWaitTime + " Sec");
		 */			


	}

	//	public void sendInput(By obj, String valuetoType) throws InterruptedException {
	//		if (isElementDisplayed( obj, 30)) {
	//			try {
	//				driver.findElement(obj).clear();
	//				driver.findElement(obj).sendKeys(valuetoType);
	//			} catch (StaleElementReferenceException e) {
	//				//e.printStackTrace();
	//				// driver.navigate().refresh();
	//				System.out.println("Test");
	//				driver.findElement(obj).clear();
	//				driver.findElement(obj).sendKeys(valuetoType);
	//			}
	//		} else {
	//			JavascriptExecutor js = (JavascriptExecutor) driver;
	//			js.executeScript("arguments[0].value=arguments[1];", driver.findElement(obj), valuetoType);
	//		}
	//	}

	public void sendInput(By object, String value) throws InterruptedException{
		List<WebElement> elements = null;
		isElementDisplayed( object, 20);
		elements = driver.findElements(object);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
				elements.get(i).clear();
				elements.get(i).sendKeys(value);
				break;
			}
		}
	}


	public  boolean exists(By by) {
		boolean isPresent;
		try {
			driver.findElement(by);
			isPresent = true;
		} catch (Exception e) {
			isPresent = false;
		}
		return isPresent;
	}

	public String getPlaceHolderFromTextBox(By obj){
		String sValue="";
		List<WebElement> elements = null;
		try
		{
			isElementDisplayed(obj, 30);
			elements = driver.findElements(obj);
			for(int i=0; i<elements.size(); i++){
				if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
					sValue = elements.get(i).getText();
				}		
			}
		}
		catch(Exception e)
		{
			System.out.println(e);

		}
		return sValue.trim();
	}

	public String getTextFromTextBox(By obj){
		String sValue="";
		List<WebElement> elements = null;
		try
		{
			if (isElementDisplayed(obj, 30))
			{
				elements = driver.findElements(obj);
				for(int i=0; i<elements.size(); i++){
					if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
						sValue = elements.get(i).getAttribute("value").toString();
					}
				}		
			}
		}
		catch(Exception e)
		{
			System.out.println(e);

		}
		return sValue.trim();
	}


	public int getRowCountFromWebTable ( By obj){

		return driver.findElements(obj).size();

	}

	public int getColCountFromWebTable ( By obj){

		return driver.findElements(obj).size();

	}

	public String getCellDataFromWebTable ( By obj, int row, int col, String objPath){

		String path=null;
		path=objPath.replaceFirst("<row>",Integer.toString(row)).replaceFirst("<col>",Integer.toString(col));
		return driver.findElement(obj).findElement(By.xpath(path)).getText().trim();
	}

	public String getHeadingTextFromWebTable ( By obj, int row, int col, String objPath){

		String path=null;
		path=objPath.replaceFirst("<row>",Integer.toString(row)).replaceFirst("<col>",Integer.toString(col));
		return driver.findElement(obj).findElement(By.xpath(path)).getText().trim();
	}

	public void setCellDataToWebTable ( By obj, int row, int col, String val, String objPath){

		String path=null;
		path=objPath.replaceFirst("<row>",Integer.toString(row)).replaceFirst("<col>",Integer.toString(col));
		driver.findElement(obj).findElement(By.xpath(path)).click();
		driver.switchTo().activeElement().sendKeys(val);
	}

	public void selectFromWebList (By object, String optionToSelect) throws InterruptedException{


		List<WebElement> elements = null;
		isElementDisplayed( object, 20);
		elements = driver.findElements(object);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){

				Select dropdown=new Select(elements.get(i));
				dropdown.selectByVisibleText(optionToSelect);
				break;
			}
		}
	}

	public void selectWebListInWebTable(By obj, int row, int col, String val, String objPath)
	{
		String path=null;
		path=objPath.replaceFirst("<row>",Integer.toString(row)).replaceFirst("<col>",Integer.toString(col));
		driver.findElement(obj).findElement(By.xpath(path)).click();
		driver.switchTo().activeElement().sendKeys(val);
	}


	public String CurrentDate(){

		Date cal = new Date();
		SimpleDateFormat CurDate = new SimpleDateFormat("yyyy-MM-dd");
		String dateformat = CurDate.format(cal);
		return dateformat;

	}	

	public static String ReadFile(String sTestFile) throws Exception{

		String TestFile = sTestFile;
		File FC = new File(TestFile);
		FC.createNewFile();
		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";
		String sTemp="";
		//Loop to read all lines one by one from file and print It.
		while((sTemp = BR.readLine())!= null){
			sTemp= 
					//Content =	Content + "\n" + sTemp;
					Content = new StringBuffer (Content).append(sTemp).append("\n").toString();
		}
		BR.close();


		return Content;
	}

	public boolean isAlertPresent()

	{
		try{
			driver.switchTo().alert();
			return true;


		}
		catch(Exception e)
		{
			return false;
		}


	}

	public boolean isTextDisplayed( String text, int timeout) throws InterruptedException{
		boolean flag = false;
		for(int i=0;i<timeout;i++){
			try{
				WebElement pageBody = driver.findElement(By.xpath("//body"));
				if(pageBody.getText().toLowerCase().contains(text.toLowerCase())){
					flag=true;
					break;
				}
			}catch(Exception e){
				Thread.sleep(500);
			}
		}

		return flag;

	}

	public void verifyText( By obj, String text, int timeout) throws InterruptedException{
		boolean flag = false;
		for(int i=0;i<timeout;i++){
			try{
				WebElement object = driver.findElement(obj);
				if(object.getText().contains(text)){
					result.updateResult(driver, Status.PASS, text+" is displayed");
					flag=true;
					break;
				}
			}catch(Exception e){
				Thread.sleep(500);
			}
		}

		if(!flag)
			result.updateResult(driver, Status.FAIL, text+" is not displayed");

	}

	public void verifyText( String text, int timeout) throws InterruptedException{
		boolean flag = false;
		for(int i=0;i<timeout;i++){
			try{
				WebElement pageBody = driver.findElement(By.xpath("//body"));
				if(pageBody.getText().toLowerCase().contains(text.toLowerCase()) ){
					result.updateResult(driver, Status.PASS, text+" is displayed in the page");
					flag=true;
					break;
				}
			}catch(Exception e){
				Thread.sleep(500);
			}
		}

		if(!flag)
			result.updateResult(driver, Status.FAIL, text+" is not displayed in the page");

	}

	public void verifyPage( String pageName, int timeout) throws InterruptedException{
		boolean flag = false;
		for(int i=0;i<timeout;i++){
			try{
				WebElement pageBody = driver.findElement(By.xpath("//body"));
				if(pageBody.getText().toLowerCase().contains(pageName.toLowerCase()) ){
					result.updateResult(driver, Status.PASS, pageName+" page is displayed");
					flag=true;
					break;
				}
			}catch(Exception e){
				Thread.sleep(500);
			}
		}

		if(!flag)
			result.updateResult(driver, Status.FAIL, pageName+" is not displayed");

	}


	public void verifyPLPPage( String pageName, int timeout) throws InterruptedException{
		boolean flag = false;
		WebElement pageBody = null;
		for(int i=0;i<timeout;i++){
			try{
				if(FunctionRepository.B2CExecution)
					pageBody=driver.findElement(By.xpath("//h2/span[contains(text(),'"+pageName+"')]"));
				if(FunctionRepository.B2BExecution)
					pageBody=driver.findElement(By.xpath("//span[@class='CMSBreadCrumbsCurrentItem'][contains(text(),'"+pageName+"')]"));
				if(pageBody.isDisplayed()){
					result.updateResult(driver, Status.PASS, pageName+" page is displayed");
					flag=true;
					break;
				}
			}catch(Exception e){
				Thread.sleep(500);
			}

		}

		if(!flag)
			result.updateResult(driver, Status.FAIL, pageName+" is not displayed");

	}

	public int getNumberOfExistance(By element){
		List<WebElement> elements = driver.findElements(element);
		if(elements.size()>0)
			return elements.size();
		else
			return 0;
	}
	
	public int getRandNumFromMultiExistance(By element){
		int number = getNumberOfExistance(element);
		Random rand = new Random();
		int num = rand.nextInt(number);
		return num;
	}

	public void scrollToElement(By obj) throws InterruptedException{
		WebElement element = driver.findElement(obj);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public int generateRandomNum(int limit){
		Random rand = new Random();
		int value = rand.nextInt(limit);
		return value;
	}

	public void mouseHover(By object) throws InterruptedException{
		List<WebElement> elements = null;
		isElementDisplayed( object, 10);
		elements = driver.findElements(object);
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).getSize().height>1 && elements.get(i).getSize().width>1){
				Actions action = new Actions(driver);
				action.moveToElement(elements.get(i)).build().perform();
				break;
			}
		}



	}

	public void pressKey(int key) throws InterruptedException, AWTException{
		//		Actions action = new Actions(driver);
		//		action.keyDown(key).perform();
		//		Thread.sleep(500);
		//		action.keyUp(key).perform();

		Robot robot = new Robot();
		robot.keyPress(key);
		Thread.sleep(100);
		robot.keyRelease(key);
	}


	public String genRandomAlphaNumString(int lenght){
		Random rand = new Random();
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String randString = "";
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<lenght; i++)
			builder.append(str.charAt(rand.nextInt(str.length())));
		randString = builder.toString();
		return randString;
	}

	public String genRandomAlphaString(int lenght){
		Random rand = new Random();
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randString = "";
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<lenght; i++)
			builder.append(str.charAt(rand.nextInt(str.length())));
		randString = builder.toString();
		return randString;
	}

	public String genRandomEMail(int lenght){
		Random rand = new Random();
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String randString = "";
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<lenght; i++)
			builder.append(str.charAt(rand.nextInt(str.length())));
		randString = builder.toString()+"@jafra.com";
		return randString;
	}

	public String genRandomNumberString(int lenght){
		Random rand = new Random();
		String str = "1234567890";
		String randString = "";
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<lenght; i++)
			builder.append(str.charAt(rand.nextInt(str.length())));
		randString = builder.toString()+"@jafra.com";
		return randString;
	}

	public boolean isPageLoadingSuccessfull() throws InterruptedException{
		isElementDisplayed(GlobalObjects.loadingIcon, 5);
		boolean pageLoaded = false;
		for(int i=0; i<60; i++){
			if(!isElementDisplayed(GlobalObjects.loadingIcon, 2)){
				pageLoaded= true;
				break;
			}
			else
				Thread.sleep(1000);
		}
		return pageLoaded;
	}

	public void acceptAlert(){
		try{
			driver.switchTo().alert().accept();
		}
		catch(Exception e){
			
		}
	}
}
