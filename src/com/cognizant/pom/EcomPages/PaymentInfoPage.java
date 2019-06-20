package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.library.CommonFunctions;
import com.cognizant.library.FunctionRepository;
import com.cognizant.report.ResultGenerator;
import com.cognizant.support.ScriptHelper;
import com.cognizant.util.AccessTestData;

public class PaymentInfoPage extends FunctionRepository{
	public WebDriver driver;
	public AccessTestData dataTable;
	public ResultGenerator result;
	public String defaultDataSheet;

	public PaymentInfoPage(ScriptHelper scriptHelper){
		super(scriptHelper);
		this.driver = scriptHelper.getDriver();
		this.dataTable = scriptHelper.getDataTable();
		this.result = scriptHelper.getResult();
		defaultDataSheet = FrameworkProperties.getProperties().getProperty("TestDataSheet");
	}
	
	CommonFunctions commonFunctions = new CommonFunctions(new ScriptHelper(driver, dataTable, result));
	
	
	//public static By makePaymentTextBox = By.id("inputMonto");
	//public static By addNewPayment = By.xpath("//a[contains(text(),'Add new payment')]");
	//public static By nameOnCard = By.xpath("//label[text()='Name on Card']/following-sibling::input");
	//public static By cardType = By.xpath("//label[text()='Card Type']/following-sibling::div//select");
	//public static By cardNumber = By.xpath("//label[text()='Card Number']/following-sibling::input");
	//public static By expMoth = By.xpath("//option[text()='MM']/parent::select");
	//public static By expYear = By.xpath("//option[text()='YYYY']/parent::select");
	//public static By cvvNumber = By.xpath("//label[text()='CVV Number']/following-sibling::div//input");
	//public static By cartTotal = By.xpath("//label[contains(text(),'Shopping Cart Order Total:')]//following-sibling::div//span");
	 public static By makePaymentTextBox = By.id("inputMonto");
	 public static By addNewPayment = By.xpath("//a[contains(text(),'Add new payment')]");
	 public static By nameOnCard = By.xpath("//input[@id='c-cardname']");
     public static By cardNumber = By.xpath("//input[@id='c-cardnumber']");
	public static By expMoth = By.xpath("//option[text()='MM']/parent::select");
	 public static By expYear = By.xpath("//option[text()='YYYY']/parent::select");
	 public static By cvvNumber = By.xpath("//input[@id='c-cvv']");
     public static By cartTotal = By.xpath("//label[contains(text(),'Shopping Cart Order Total:')]//following-sibling::div//span");


	
	public static By firstNameTextBox = By.xpath("//input[@placeholder='First Name']");
	public static By lastNameTextBox = By.xpath("//input[@placeholder='Last Name']");
	public static By address1BillingTextBox = By.xpath("//label[text()='Address 1']/following-sibling::input");
	public static By address2BillingTextBox = By.xpath("//label[text()='Address 2']/following-sibling::input");
	public static By cityBillingTextBox = By.xpath("//label[text()='City']/following-sibling::input");
	public static By stateBillingDropDown(){
		if(FunctionRepository.B2BExecution)
			return By.xpath("//select[@class='billing_address_state form-control']");
		if(FunctionRepository.B2CExecution)
			return By.xpath("//select[@id='AddressState']");
		
		return null;
	}
	public static By stateBillingDropDown = By.xpath("//select[@id='AddressState']");
	public static By zipBillingTextBox = By.xpath("//label[text()='Zip Code']/following-sibling::input");
	
	public static By continueButton = By.xpath("//button[contains(text(),'CONTINUE')]");
	public static By savepaymentb2b=By.xpath("//button[@id='btnContinuePayment']");
	public  static By NextButton=By.xpath("//button[@class='btn btn-default']");
	public static By paymentContinueButton=By.xpath("//button[@id='btnContinuePayment']");
	public static By Addnewbillinginformation=By.xpath("//a[contains(text(), 'Add new billing information')]");
	
}
