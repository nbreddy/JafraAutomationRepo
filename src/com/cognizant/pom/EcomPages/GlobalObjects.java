package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;

public class GlobalObjects {
	

	public static String fName="";
	public static String lName="";
	public static String itemName="";
	public static String eMail="";
	
	public static String personalWebsiteURL="";
	public static By consultantName = By.xpath("//div[@class='tp-my-info']/p/strong");
	
	public static By loadingIcon = By.id("jafra-loading");
	public static By headerMyAccount = By.xpath("//a[contains(text(),'My Account')]");
	public static By headerSignOut = By.xpath("//a[contains(text(),'Sign Out')]");
	public static By consultantLogIn = By.xpath("//a[contains(text(),' Consultant Log In')]");
	public static By megaMenuBar = By.xpath("//div[@class='nav-slide js-slide-hidden']");
	public static By sponsoringLink = By.xpath("//a[contains(text(),'Sponsoring')]");
	
	public static By searchIcon(){
		if(FunctionRepository.B2BExecution)
			return By.xpath("//i[@class='fa fa-search']");
		if(FunctionRepository.B2CExecution)
			return By.xpath("(//span[@class='search'])[1]");
		
		return null;
	}
	
	public static By searchTextBox(){
		if(FunctionRepository.B2BExecution)
			return By.xpath("//input[@id='txtTopSearch']");
		if(FunctionRepository.B2CExecution)
			return By.xpath("//input[@id='js-site-search-input']");
		return null;
	}
	
	//input[@id='txtTopSearch']
	
//	public static By searchIcon = By.xpath("(//span[@class='search'])[1]");
//	public static By searchTextBox = By.xpath("//input[@id='js-site-search-input']");
	public static By suggestedCategory = By.xpath("//div[@class='name']");
	public static By suggestedItem = By.xpath("(//span[@class='title']/strong)[3]");
	public static By errorPage = By.xpath("//h3[contains(text(),'Oops! Something went wrong.')]");
	
	public static String skinCare = "Skin Care";
	public static String fragrance = "Fragrance";
	public static String makeUp = "MakeUp";
	public static String bathNBody = "Bath & Body";
	public static String eCatalog = "E Catalog";
	public static String beautyScene = "Beauty Scene";
	public static String specialOffers = "Special Offers";
	public static String becomeAConsultant = "Become A Consultant";
	public static String shop = "SHOP";
	public static String myBusiness = "My Business";
	public static String whatsHappening = "What\'s happening";
	public static String sponsoring = "Sponsoring";
	
	public static String[] categoryList = {skinCare,fragrance,makeUp,bathNBody};
	public static String[] categoryPage = {"Skincare","Fragrance","Makeup","Bath & Body"};
	
	public static By category(String category){
	 return By.xpath("(//a[contains(text(),\""+category+"\")])");	
	}
	
	public static By shopMenu(){
		 return By.xpath("(//a[contains(text(),'SHOP')])");	
		}
	
	public static By subCategory(String subCategory){
		 return By.xpath("(//a[contains(text(),'"+subCategory+"')])");	
		}
	
	public static By cartCount(){
		if(FunctionRepository.B2BExecution)
			return By.xpath("//span[contains(@id, 'MiniCart_totalItems')]");
		if(FunctionRepository.B2CExecution)
			return By.xpath("//*[@code='miniCart_items_label']");
		return null;
		}
	
	
	public static By viewShoppingCart(){
		return By.xpath("//a[contains(text(),'VIEW SHOPPING CART')]");
	}
	

	public static By contactUsLink(){
		if(FunctionRepository.B2CExecution)
			return By.xpath("//a[contains(@id,'contactUslLink')]");
		if(FunctionRepository.B2BExecution)
			return By.xpath("//a[contains(@id,'contactUslLink')]");
		return null;
		}
	
	public static By faceBookIcon(){
		 return By.xpath("//i[@class='fa fa-facebook']");	
		}
	public static By youtubeIcon(){
		 return By.xpath("//i[@class='fa fa-youtube']");	
		}
	public static By instagramIcon(){
		 return By.xpath("//i[@class='fa fa-instagram']");	
		}
	public static By pinterestIcon(){
		 return By.xpath("//i[@class='fa fa-pinterest']");	
		}
	public static By twitterIcon(){
		 return By.xpath("//i[@class='fa fa-twitter']");	
		}
	
	public static String contactUs = "CONTACT US";
	public static String chooseCountry = "CHOOSE COUNTRY";
	public static String aboutJafra = "ABOUT JAFRA";
	public static String clientCare = "CLIENT CARE";
	public static String dSA = "DSA";
	public static String joinJafra = "JOIN JAFRA";
	public static String consultantCare = "CONSULTANT CARE";
	public static String aboutJafraPlus = "+About Jafra";
	public static String dSAPlus = "+DSA";
	public static String terms = "Terms";
	public static String policiesNProcedures = "POLICIES AND PROCEDURES";
	public static String privacy = "PRIVACY";
	public static By skin= By.xpath("//a[contains(text(),'Skin Care')]");
	public static By skincare= By.xpath("//h1[contains(text(),'Skin, transformed')]");
	public static By BUYNOW= By.xpath("(//a[@class='btn btn-success'])[4]");
	public static By ADDTOCART=By.xpath("//button[@class='btn  addcartbtn ']");  
	public static By proceedtochecout=By.xpath("//a[contains(text(),'Proceed to checkout')]");
	public static By cartbutton=By.xpath("//i[@class='fa fa-shopping-cart']");
	public static By viewshoppingcart=By.xpath("//a[@id='lnkSearch']");
	public static By shippingaddressNext=By.xpath("//button[contains(text(),'NEXT')]");
	public static By paymentinformationnext=By.xpath("//button[contains(text(),'NEXT')]");
	public static By reviwandpaceorder=By.xpath("//input[@id='chcktemscontd']");
	
	
	

	
	
	public String[] footerLinks={contactUs, chooseCountry, aboutJafra, clientCare, dSA, joinJafra}; 
	public static By links(String name){
		 return By.xpath("(//a[contains(text(),'"+name+"')])");	
		}
	
	
	
}
