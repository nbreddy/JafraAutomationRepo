package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;
import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;



public class MyAccSignInPage extends FunctionRepository{
	
	public MyAccSignInPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	public static By userIdTextBox = By.xpath("//input[@id='j_username']");
	public static By passwordTextBox = By.xpath("//input[@id='j_password']");
	public static By submitButton = By.xpath("//form[@id='loginForm']//button[@type='submit']");
	public static By signInButton = By.xpath("//a[contains(text(),'SIGN IN')]");
	public static By becomeAConsultant = By.xpath("//a[text()='Become a Consultant']");
	public static By newCustEmail = By.xpath("//input[@name='email']");
	public static By createAnAccountButton = By.xpath("//button[text()='Create an account']");

}
