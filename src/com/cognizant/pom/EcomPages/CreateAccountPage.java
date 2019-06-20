package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class CreateAccountPage {
	public static By firstNameTextBox = By.name("firstName");
	public static By lastNameTextBox = By.name("lastName");
	public static By phoneNumberTextBox = By.name("mobileNumber");
	public static By passwordTextBox = By.name("pwd");
	public static By verPasswordTextBox = By.name("checkPwd");
	public static By createAccountButton = By.id("accRegister_submit");
	public static By welcomeToJafraCloseButton = By.id("popUp_Register_btnClose");
}
