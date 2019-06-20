package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;

public class PDPPage extends FunctionRepository {
	public PDPPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
//	public static By addToCartButton(){
//		if(FunctionRepository.B2BExecution)
//			return By.xpath("//button[contains(text(),'Add to cart')]");
//		if(FunctionRepository.B2CExecution)
//			return By.xpath("//button[contains(text(),'Add to cart')]");
//		return null;
//	}
	
	public static By itemNmae = By.xpath("//h1");
	public static By addToCartButton = By.xpath("//button[contains(@id,'addToCartButton')]");
	public static By itemNmaeSecInConfirmPopUp = By.xpath("//div[@class='product-info']");
	public static By proceedToCheckoutButton = By.xpath("//a[text()='Proceed to checkout']");
	public static By addToWishlistLink = By.xpath("//a[contains(text(),'Add To Wishlist')]");
	
	public static By selectReplenishment = By.xpath("//select[@id='replenishmentFrequency']");
	public static By saveToCart = By.xpath("//button[contains(text(),'I Agree, Save to Cart')]");
}
