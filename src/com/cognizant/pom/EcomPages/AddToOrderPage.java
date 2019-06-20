package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class AddToOrderPage {
	public static By addToOrderPage = By.xpath("//h3[contains(text(),'1. Add to Order')][@aria-selected='true']");
	
	public static By addToCartButton = By.xpath("//input[@value='ADD TO CART']");
	
	public static By itemNumberTextBox(int index){
		return By.xpath("//tr[@id='order_entry_row_"+index+"']//td[@class='product_code_col']/input");
	}
	
	public static By removeLink(int index){
		return By.xpath("//tr[@id='order_entry_row_"+index+"']//a[@class='remove_product linkgrey']");
	}
	
	public static By quantityTextBox(int index){
		return By.xpath("//tr[@id='order_entry_row_"+index+"']//input[@class='quantity_check']");
	}
	
}
