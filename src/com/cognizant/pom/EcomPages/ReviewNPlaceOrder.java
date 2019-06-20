package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;

public class ReviewNPlaceOrder extends FunctionRepository{
	public ReviewNPlaceOrder(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	public static By termsConditionsCheckbox = By.id("chcktemscontd");
	public static By placeOrderButton(){
		if(B2BExecution)
			return By.xpath("//button[contains(text(),'Place Order')]");
		if(B2CExecution)
			return By.xpath("//input[@id='placeOrder']");
		return null;
	}
	
}
