package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;

public class ShoppingCartPage extends FunctionRepository{
	public ShoppingCartPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	public static By continueButton = By.xpath("//a[contains(text(),'CONTINUE')]");
	public static By AchievedpromotionnextButton = By.xpath("//button[@class='btn btn-red-light']");
	public static By nextButton = By.xpath("//button[contains(text(),'NEXT')]");
	
	public static By shoppingCartPage(){
		if(B2CExecution)
			return By.xpath("//h1[contains(text(),'SHOPPING CART')]");
		if(B2BExecution)
			return By.xpath("//h3[contains(text(),'2. Shopping Cart')]");
		return null;
	}
	
	public static By removeLinkButton(){
		if(B2BExecution)
			return By.xpath("//a[contains(text(),'Remove')][@class='linkgrey removeCart']");
		if(B2CExecution)
			return By.xpath("//button[@class='btn btn-primary js-remove-entry-button']");
		return null;
	}
	
	public static By removeLink(String itemName){
		if(B2CExecution)
			return By.xpath("//tr//a[contains(text(),'"+itemName+"')]/parent::td/parent::tr//a[contains(text(),'Remove')]");
		if(B2BExecution)
			return By.xpath("//tr//a[contains(text(),'"+itemName+"')]/parent::td/parent::tr//a[contains(text(),'Remove')]");
		return null;
	}
	
	public static By checkoutButton = By.xpath("//a[contains(text(),'CONTINUE')]");
	public static By quantityTextBox = By.xpath("//input[@class='inputProductQtyShoppingCart']");
	public static By updateCartButton = By.xpath("//a[@id='updateCartLink']");
	public static By viewMoreLink = By.xpath("//a[@class='link01 viewmore']");
	public static By firstAddItemName = By.xpath("(//td[@class='product_description']//span)[1]");
	
	
}
