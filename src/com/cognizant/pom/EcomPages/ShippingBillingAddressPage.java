package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;

public class ShippingBillingAddressPage {
	public static By firstNameTextBox = By.xpath("//label[contains(text(),'First Name')]/following-sibling::div/input");
	public static By lastNameTextBox = By.xpath("//label[contains(text(),'Last Name')]/following-sibling::div/input");
	public static By address1ShippingTextBox = By.xpath("//label[contains(text(),'Address')][contains(text(),'1')]/following-sibling::div/input");
	public static By address2ShippingTextBox = By.xpath("//label[contains(text(),'Address')][contains(text(),'2')]/following-sibling::div/input");
	public static By cityShippingTextBox = By.xpath("//label[contains(text(),'City')]/following-sibling::div/input");
	public static By stateShippingDropDown = By.xpath("//label[contains(text(),'State')]/following-sibling::div/Select");
	public static By zipShippingTextBox = By.xpath("//label[contains(text(),'Zip')]/following-sibling::div/input");
	public static By coShippingTextBox = By.xpath("//label[contains(text(),'C/O')]/following-sibling::div/input");
	public static By address1SuggestionList = By.xpath("//li[@class='ui-menu-item']");
	public static By address2SuggestionList = By.xpath("//li[@class='ui-menu-item']");
	public static By paymentOption1 = By.xpath("//input[@value='option1']");
	public static By paymentOption2 = By.xpath("//input[@value='option2']");
	public static By paymentOption4 = By.xpath("//input[@value='option4']");
	public static By nextButtonb2c = By.xpath("//button[@id='submit_address']");
	public static By savePaymentb2c=By.xpath("//input[@class='btn btn-default']");
	
	public static By nextButton(){
		if(FunctionRepository.B2CExecution)
			return By.xpath("//button[contains(text(),'Next')]");
		if(FunctionRepository.B2BExecution)
			return By.xpath("//button[contains(text(),'NEXT')]");
		return null;
	}
//	public static By nextButton = By.xpath("//button[text()='Next']");
	public static By creditcardNameTextBox = By.id("credit-card-name-id");
	public static By creditcardTypeDropDown = By.id("credit-card-type-id");
	public static By creditcardNumberTextBox = By.id("credit-card-number-id");
	public static By creditcardExpMonthDropDown = By.id("credit-card-month-id");
	public static By creditcardExpYearDropDown = By.id("credit-card-year-id");
	public static By creditcardSecurityCodeTextBox = By.id("credit-card-code-id");
	
	
	public static By shipToNewAddressLink = By.xpath("//a[contains(text(),'Ship to New Address')]");
}
