package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class ServiceNow {
	public static By userNameTextBox = By.xpath("//input[@id='user_name']");
	public static By passwordTextBox = By.xpath("//input[@id='user_password']");
	public static By loginButton = By.xpath("//button[@id='sysverb_login']");
	public static By loggedInPage = By.xpath("//div[@class='kb_search_container']");
	
	public static By changeCreateNewLink = By.xpath("//a[text()='Change']//parent::span//following-sibling::ul//a[text()='Create New']");
	
	public static By changeRequestPage = By.xpath("//span[text()='Change Request']");
	
	public static By attachLink = By.xpath("//button[@class='btn btn-icon icon-paperclip navbar-btn']");
	public static By chooseFile = By.xpath("//input[@id='attachFile']");
	public static By attachButton = By.xpath("//input[@id='attachButton']");
	public static By removeButton = By.xpath("//input[@id='removeButton']");
	public static By popUpCross = By.xpath("//button[@class='btn btn-icon close icon-cross']");
}
