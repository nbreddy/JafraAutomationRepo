package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class PLPPage {
	public static By searchResulText = By.xpath("//strong[@class='text-result']");
	public static By addToCartButton (int index){
		return By.xpath("(//button[contains(@id,'addToCartButton')])["+index+"]");
		//button[contains(@id,'addToCartButton')])[6]
	}
	public static By addToCartButton (){
		return By.xpath("//button[contains(@id,'addToCartButton')]");
	}
	
	public static By dealDescription (int index){
		return By.xpath("(//p[contains(@id,'proddesc')])["+index+"]");
	}
	public static By dealDescription (){
		return By.xpath("(//p[contains(@id,'proddesc')])");
	}
	
	public static By addToCartMyOffer (){
		return By.xpath("//input[contains(@id,'btnKitAddToCart')]");
	}
	
	public static By buyNowButton (){
		return By.xpath("(//a[contains(text(),'BUY NOW')])");
	}
	
	public static By buyNowButton (int index){
		//return By.xpath("(//a[contains(text(),'BUY NOW')])["+index+"]");
		return By.xpath("//a[text()='BUY NOW']//ancestor::div[1]");
	}
    public static By itemName (int index){
        return By.xpath("(//h3[@class='item-name'])["+index+"]");
}

}
