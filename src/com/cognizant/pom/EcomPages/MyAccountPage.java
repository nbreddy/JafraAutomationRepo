package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

import com.cognizant.library.FunctionRepository;
import com.cognizant.support.ScriptHelper;

public class MyAccountPage extends FunctionRepository{
	public MyAccountPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	public static By generalLink = By.xpath("//a[contains(text(),'General')]");
	public static By shippingMailingLink = By.xpath("//a[contains(text(),'Shipping and Mailing')]");
	public static By paymentBillingLink = By.xpath("//a[contains(text(),'Payment and Billing')]");
	public static By preferencesLink = By.xpath("//a[contains(text(),'Preferences')]");
	public static By websiteManagementLink = By.xpath("//a[contains(text(),'Website Management')]");
	public static By accountActivityLink = By.xpath("//a[contains(text(),'Account Activity')]");
	public static By orderHistoryLink = By.xpath("//a[contains(text(),'Order History')]");
	public static By paymentHistoryLink = By.xpath("//a[contains(text(),'Payment History')]");
	
	public static By signInInfoLink = By.xpath("//a[contains(text(),'Sign-in Information')]");
	public static By billilngInfoLink = By.xpath("//a[contains(text(),'Billing Information')]");
	public static By shippingAddLink = By.xpath("//a[contains(text(),'Shipping Address')]");
//	public static By orderHistoryLink = By.xpath("//a[contains(text(),'Order History')]");
	public static By myWishListLink = By.xpath("//a[contains(text(),'My Wish List')]");
	public static By myReviewsLink = By.xpath("//a[contains(text(),'My Reviews')]");
	public static By websiteOn = By.xpath("//button[@id='ON']");
	public static By websiteOff = By.xpath("//button[@id='OFF']");
	public static By save = By.xpath("//button[contains(text(),'Save')]");
	public static By websiteURL = By.xpath("//li[contains(text(),'URL:')]/a");
	public static By wishListRemoveButton = By.xpath("//input[@class='btn-cart wishlistRemoveBtn']");
	
	
	public static By editbutton=By.xpath("(//a[@class='btn btn-red-light'])[2]"); 

	  public static By Firstname = By.xpath("//input[@id='shippingAddrFirstName']");
    public static By Lastname = By.xpath("//input[@id='shippingAddrLastName']");
    public static By Business = By.xpath("//input[@id='shippingAddrBusinessCO']");
    public static By address1 = By.xpath("//input[@id='shippingAddrLine1']");
    public static By address2 = By.xpath("//input[@id='shippingAddrLine2']");
    public static By city = By.xpath("//input[@id='shippingAddrCity']");
    public static By state = By.xpath("//select[@id='shippingAddrState']");
    public static By zipcode = By.xpath("//input[@id='shippingAddrZip']");
    public static By shipopingmethod = By.xpath("//select[@id='editShippingAddr_shipping_condition']");
    public static By savebuttonshippingmethod = By.xpath("//button[@id='billing_submit']");
    
    public static By b2cshippingaddress = By.xpath("//a[@title='Shipping Address']");
    public static By ADDNEW = By.xpath("//a[@id='lnkAddNewAddress']");
    public static By B2CFirstname = By.xpath("//input[@id='txtName']");
	   public static By B2CADDRESS1 = By.xpath("//input[@id='shippingAddrLine1']");
    public static By B2CADDRESS2 = By.xpath("//input[@id='shippingAddrLine2']");
    public static By B2CCITY = By.xpath("//input[@id='shippingAddrCity']");
	   public static By B2CSTATE = By.xpath("//select[@id='shippingAddrState']");
    public static By B2Czipcode = By.xpath("//input[@id='shippingAddrZip']");
    public static By b2cphoneno = By.xpath("//input[@id='shippingAddrPhone']");
    public static By B2Csaveaddress = By.xpath("//input[@class='btn btn-default addressAddEdit_submit save-add']");
    public static By b2cedit = By.xpath("//a[@class='edit']");

	
	public static By myAccountPage(){
		if(B2BExecution)
			return By.xpath("//a[contains(text(),'MY ACCOUNT')][@class='title']");
		if(B2CExecution)
			return By.xpath("//strong[contains(text(),'My Account')]");	
		return null;
		}
	
	public static By generalPage(){
		 return By.xpath("//h1[contains(text(),'GENERAL')]");	
		}
	public static By shippingMailingPage(){
		 return By.xpath("//h1[contains(text(),'SHIPPING AND MAILING')]");	
		}
	public static By paymentBillingPage(){
		 return By.xpath("//h1[contains(text(),'Payment And Billing')]");	
		}
	public static By preferencesPage(){
		 return By.xpath("//h1[contains(text(),'PREFERENCES')]");	
		}
	public static By websiteManagementPage(){
		 return By.xpath("//h1[contains(text(),'WEBSITE MANAGEMENT')]");	
		}
	public static By accountActivityPage(){
		 return By.xpath("//h1[contains(text(),'ACCOUNT ACTIVITY')]");	
		}
	public static By myOrdersPage(){
		 return By.xpath("//a[contains(text(),'MY ORDERS')]");	
		}
	public static By paymentHistoryPage(){
		 return By.xpath("//h1[contains(text(),'Payment History')]");	
		}
	
	public static By signInInfoPage(){
		 return By.xpath("//li[contains(text(),'Sign in Information')]");	
		}
	public static By billingInfoPage(){
		 return By.xpath("//li[contains(text(),'Billing Information')]");	
		}
	public static By shippingAddressPage(){
		 return By.xpath("//li[contains(text(),'Shipping Address')]");	
		}
	public static By orderHistoryPage(){
		 return By.xpath("//li[contains(text(),'Order History')]");	
		}
	public static By myWishlistPage(){
		 return By.xpath("//li[contains(text(),'My wishlist')]");	
		}
	public static By myReviewsPage(){
		 return By.xpath("//li[contains(text(),'My Reviews')]");	
		}
	
	public static By orderElement(){
		 return By.xpath("//div[contains(text(),'Order')]");	
		}
	
	public static By dateElement(){
		 return By.xpath("//div[contains(text(),'Date')]");	
		}
	public static By statusElement(){
		 return By.xpath("//div[contains(text(),'Status')]");	
		}
}
