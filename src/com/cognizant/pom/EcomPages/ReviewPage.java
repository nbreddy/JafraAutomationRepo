package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class ReviewPage {
	public static By submitButton = By.xpath("//button[contains(text(),'SUBMIT')]");
	public static By returnToOrderButton = By.xpath("//a[contains(text(),'Return to Order')]");
	public static By placeOrderButton = By.xpath("//button[contains(text(),'PLACE ORDER')]");
}
