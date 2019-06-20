package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;

public class ContactUsPage {
	public static By contactUsPage(){
		if(FunctionRepository.B2CExecution)
			return By.xpath("//h1[contains(text(),'CONTACT US')]");
		if(FunctionRepository.B2BExecution)
			return By.xpath("//h1[contains(text(),'CONSULTANT CARE')]");
		return null;
		}
	public static By topicDropDown(){
		 return By.name("topic");	
		}
	public static By firstNameTextBox(){
		 return By.name("firstName");	
		}
	public static By lastNameTextBox(){
		 return By.name("lastName");	
		}
	public static By eMailTextBox(){
		 return By.name("email");	
		}
	public static By phoneTextBox(){
		 return By.name("phoneNumber");	
		}
	public static By messageTextBox(){
		 return By.name("comments");	
		}
	public static By submitButton(){
		return By.xpath("//button[contains(text(),'Submit')]");	
		}
}
