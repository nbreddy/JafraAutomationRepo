package com.cognizant.library;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xwpf.usermodel.examples.UpdateEmbeddedDoc;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.config.FrameworkProperties;
import com.cognizant.config.Status;
import com.cognizant.pom.EcomPages.AddToOrderPage;
import com.cognizant.pom.EcomPages.ContactUsPage;
import com.cognizant.pom.EcomPages.CreateAccountPage;
import com.cognizant.pom.EcomPages.GlobalObjects;
import com.cognizant.pom.EcomPages.HomePage;
import com.cognizant.pom.EcomPages.JoinJafraConsultantPage;
import com.cognizant.pom.EcomPages.MyAccSignInPage;
import com.cognizant.pom.EcomPages.MyAccountPage;
import com.cognizant.pom.EcomPages.PDPPage;
import com.cognizant.pom.EcomPages.PLPPage;
import com.cognizant.pom.EcomPages.PaymentInfoPage;
import com.cognizant.pom.EcomPages.PrivacyPage;
import com.cognizant.pom.EcomPages.ReviewNPlaceOrder;
import com.cognizant.pom.EcomPages.ReviewPage;
import com.cognizant.pom.EcomPages.ServiceNow;
import com.cognizant.pom.EcomPages.ShippingBillingAddressPage;
import com.cognizant.pom.EcomPages.ShoppingCartPage;
import com.cognizant.report.ResultGenerator;
import com.cognizant.support.ScriptHelper;
import com.cognizant.util.AccessTestData;
import com.cognizant.util.WebDriverUtil;
import java.awt.Robot;	
import java.awt.event.InputEvent;
//import com.mongodb.binding.ClusterBinding;

public class FunctionRepository extends CommonFunctions {
	private static final String False = null;
	private WebDriver driver;
	private AccessTestData dataTable;
	private ResultGenerator result;
	private String defaultDataSheet;
	public static int imgCounter;
	public static boolean testCaseFailStatus;


	public FunctionRepository(ScriptHelper scriptHelper) {
		super(scriptHelper);
		this.driver = scriptHelper.getDriver();
		this.dataTable = scriptHelper.getDataTable();
		this.result = scriptHelper.getResult();
		defaultDataSheet = FrameworkProperties.getProperties().getProperty("TestDataSheet");
	}
	CommonFunctions commonFunctions = new CommonFunctions(new ScriptHelper(driver, dataTable, result));

	public static boolean B2CExecution;
	public static boolean B2BExecution;


	public void invokeApplication() {
		B2CExecution=false;
		B2BExecution=false;

		this.imgCounter=1;
		this.testCaseFailStatus = false;
		String url = dataTable.getData("TestData", "AUT").trim();

		//		Dimension dim = new Dimension(600, 600);
		//		driver.manage().window().setSize(dim);

		driver.get(url);

		if(url.contains("www.jafrabiz.com") || 
				url.contains("https://qa1-www.jafrabiz.com/jafrastorefront") ||
				url.contains("https://vwdcqv-uxapp124.emea.vorwerk.org:9002/jafrastorefront/jafra-us/login") ||
				url.contains("https://vwdcdv-uxapp125.emea.vorwerk.org:9002/jafrastorefront/jafra-us/login") ||
				url.contains("https://vwdcpv-uxapp154.emea.vorwerk.org:9002/jafrastorefront/jafra-us/login")) 
		
			B2BExecution=true;
		if(url.contains("www.jafra.com") ||
				url.contains("https://qa1-www.jafra.com")||
				url.contains("https://vwdcqv-uxapp124.emea.vorwerk.org:9002/?site=jafra-b2C")||
				url.contains("https://vwdcdv-uxapp125.emea.vorwerk.org:9002/?site=jafra-b2C")||
				url.contains("https://vwdcpv-uxapp154.emea.vorwerk.org:9002/?site=jafra-b2C"))
				
			B2CExecution=true;
		//WebDriverUtil.waitDriver(driver, 10);
		//ignoreHomepagePopup();
		//driver.navigate().refresh();

		String currentUrl = driver.getCurrentUrl();
		/*System.out.println(url);
		System.out.println(currentUrl);*/
		if (currentUrl.contains(url.substring(url.indexOf("//")+2)))
			result.updateResult(driver, Status.PASS, "URL "+ url +" opened successfully");
		else
		{
			System.out.println(url);
			System.out.println(currentUrl);
			result.updateResult(driver, Status.FAIL, "URL not opened, Expected- "+ url + " , Actual- "+ currentUrl );
		}

	}

	public void navigateToMyAccountSignInPage() throws InterruptedException{
		clickObject(GlobalObjects.headerMyAccount);
		verifyPage("MY ACCOUNT SIGN IN", 15);

	}

	public void navigateToMyAccountPage() throws InterruptedException{
		clickObject(GlobalObjects.headerMyAccount);
		verifyElementExistance("My Account page", MyAccountPage.myAccountPage(), 20);
		//		verifyPage("GENERAL", 15);

	}

	public void login() throws InterruptedException, AWTException{

		sendInput(MyAccSignInPage.userIdTextBox, dataTable.getData("TestData", "UserId"));

		sendInput(MyAccSignInPage.passwordTextBox, dataTable.getData("TestData", "Password"));
		result.updateResult(driver, Status.INFO, "Entrerd information");
		//		commonFunctions.pressKey(KeyEvent.VK_ENTER);
		scrollToElement(MyAccSignInPage.submitButton);
		clickObject(MyAccSignInPage.submitButton);
		//		verifyElementExistance("Logged in Page", GlobalObjects.headerSignOut, 15);
		if(isElementDisplayed(GlobalObjects.headerSignOut, 20)){
			result.updateResult(driver, Status.PASS, "User logged in successfully");
		}
		else{
			result.updateResult(driver, Status.FAIL, "User did not log in");
		}

	}

	public void loginThroughCheckout() throws InterruptedException, AWTException{

		sendInput(MyAccSignInPage.userIdTextBox, dataTable.getData("TestData", "UserId"));

		sendInput(MyAccSignInPage.passwordTextBox, dataTable.getData("TestData", "Password"));
		result.updateResult(driver, Status.INFO, "Entrerd information");
		//		commonFunctions.pressKey(KeyEvent.VK_ENTER);
		scrollToElement(MyAccSignInPage.signInButton);
		clickObject(MyAccSignInPage.signInButton);
		verifyElementExistance("Logged in Page", GlobalObjects.headerSignOut, 15);
		if(isElementDisplayed(GlobalObjects.headerSignOut, 1)){
			result.updateResult(driver, Status.PASS, "User logged in successfully");
		}
		else{
			result.updateResult(driver, Status.FAIL, "User did not log in");
		}

	}

	public void logOut() throws InterruptedException{
		clickObject(GlobalObjects.headerSignOut);
		if(B2CExecution){
			if(isElementDisplayed(GlobalObjects.consultantLogIn, 15)){
				result.updateResult(driver, Status.PASS, "User logged out successfully");
			}
			else{
				result.updateResult(driver, Status.FAIL, "User did not log out");
			}
		}
		if(B2BExecution){
			if(isElementDisplayed(MyAccSignInPage.userIdTextBox, 15)){
				result.updateResult(driver, Status.PASS, "User logged out successfully");
			}
			else{
				result.updateResult(driver, Status.FAIL, "User did not log out");
			}
		}
	}

	public void customerRegistration() throws InterruptedException{
		GlobalObjects.eMail = genRandomAlphaString(10)+"@cognizant.com";

		sendInput(MyAccSignInPage.newCustEmail, GlobalObjects.eMail);
		result.updateResult(driver, Status.INFO, "Entrerd information");
		clickObject(MyAccSignInPage.createAnAccountButton);
		isPageLoadingSuccessfull();
		verifyPage("Sign Up", 20);
		verifyPage("Create an Account", 20);
		populateCreateAccountDetails();
		scrollToElement(CreateAccountPage.createAccountButton);
		clickObject(CreateAccountPage.createAccountButton);
		isPageLoadingSuccessfull();
		verifyPage("Welcome to Jafra!", 20);
		clickObject(CreateAccountPage.welcomeToJafraCloseButton);
		if(isElementDisplayed(GlobalObjects.headerSignOut, 20)){
			dataTable.putData("TestData", "UserId", GlobalObjects.eMail);
			result.updateResult(driver, Status.PASS, "User registered successfully");
		}

	}

	public void populateCreateAccountDetails() throws InterruptedException{
		sendInput(CreateAccountPage.firstNameTextBox, "Bittu");
		sendInput(CreateAccountPage.lastNameTextBox, "Shill");
		sendInput(CreateAccountPage.phoneNumberTextBox, dataTable.getData("TestData", "PhoneNumber"));
		sendInput(CreateAccountPage.passwordTextBox, "b212345678");
		sendInput(CreateAccountPage.verPasswordTextBox, "b212345678");
		result.updateResult(driver, Status.INFO, "Entrerd information");
	}

	public void navPLPThroughCatSubCat() throws InterruptedException{
		//		CommonFunctions commonFunctions = new CommonFunctions(new ScriptHelper(driver, dataTable, result));
		String navigationData = dataTable.getData("TestData", "Navigation");
		String[] navigationPath = navigationData.toString().split("#");
		String[] category=new String[navigationPath.length-1];
		String[] subCategory=new String[navigationPath.length-1];
		for(int i=1; i<navigationPath.length; i++){
			category[i-1] = (navigationPath[i].split(">"))[0];
			subCategory[i-1] = (navigationPath[i].split(">"))[1];
		}
		if(B2BExecution)
			isElementDisplayed(GlobalObjects.shopMenu(), 30);
		for(int j=0; j<navigationPath.length-1; j++){
			if(B2BExecution)
				mouseHover(GlobalObjects.shopMenu());
			mouseHover(GlobalObjects.category(category[j]));
			clickObject(GlobalObjects.subCategory(subCategory[j]));
			for(int i=0; i<3; i++){
				if(isElementDisplayed(GlobalObjects.errorPage, 3)){
					System.out.println("Error page found");
					Thread.sleep(4000);
					driver.navigate().refresh();
					System.out.println("Browser Refreshed");
					
				}
				if(isTextDisplayed(subCategory[j],1)){
					System.out.println("PLP page found");
					break;
				}
			}
			verifyPLPPage(subCategory[j],20);
		}

	}

	public void navPLPTDirectlyhroughCat() throws InterruptedException{
		//		CommonFunctions commonFunctions = new CommonFunctions(new ScriptHelper(driver, dataTable, result));
		if(B2BExecution)
			isElementDisplayed(GlobalObjects.shopMenu(), 30);
		for(int i=0; i<GlobalObjects.categoryList.length; i++){
			if(B2BExecution)
				mouseHover(GlobalObjects.shopMenu());
			clickObject(GlobalObjects.category(GlobalObjects.categoryList[i]));
			verifyPLPPage(GlobalObjects.categoryPage[i], 20);
		}

		//		CommonFunctions commonFunctions = new CommonFunctions(new ScriptHelper(driver, dataTable, result));
		//		String navigationData = dataTable.getData("TestData", "Navigation");
		//		String[] navigationPath = navigationData.toString().split("#");
		//		String[] category=new String[navigationPath.length-1];
		//		String[] subCategory=new String[navigationPath.length-1];
		//		for(int i=1; i<navigationPath.length; i++){
		//			category[i-1] = (navigationPath[i].split(">"))[0];
		//			subCategory[i-1] = (navigationPath[i].split(">"))[1];
		//		}
		//		for(int j=0; j<navigationPath.length-1; j++){
		//			commonFunctions.mouseHover(GlobalObjects.category(category[j]));
		//			clickObject(GlobalObjects.subCategory(subCategory[j]));
		//			verifyPLPPage(subCategory[j],2);
		//		}

	}

	public void searchCategory() throws InterruptedException{
		clickObject(GlobalObjects.searchIcon());
		sendInput(GlobalObjects.searchTextBox(), dataTable.getData("TestData", "SearchKeyword"));
		result.updateResult(driver, Status.INFO, "Entrerd information");
		String catName = getObjText(GlobalObjects.suggestedCategory);
		clickObject(GlobalObjects.suggestedCategory);
		if(B2CExecution)
			verifyText(PLPPage.searchResulText, catName, 20);
		if(B2BExecution)
			verifyPage("RESULTS FOUND FOR \""+catName.toUpperCase()+"\"", 20);
	}

	public void searchItem() throws InterruptedException{
		clickObject(GlobalObjects.searchIcon());
		String searchText = dataTable.getData("TestData", "SearchKeyword");
		sendInput(GlobalObjects.searchTextBox(), searchText);
		result.updateResult(driver, Status.INFO, "Entrerd information");
		String itemName = getObjText(GlobalObjects.suggestedItem);
		GlobalObjects.itemName = itemName;
		clickObject(GlobalObjects.suggestedItem);
		verifyText(PDPPage.itemNmae, itemName, 20);
		verifyText(searchText, 20);
	}

	public String getItemNameFromPDP(){
		String itemName = getObjText(PDPPage.itemNmae);
		return itemName;
	}

	public void addToCart() throws InterruptedException{
		int cartCountBefore = 0;
		int cartCount = getCartCount();
		cartCountBefore = cartCount;
		System.out.println("before: "+cartCountBefore);
		scrollToElement(PDPPage.addToCartButton);
		clickObject(PDPPage.addToCartButton);
		try{
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			clickObject(PDPPage.addToCartButton);
		}
		catch(Exception e){
			System.out.println("No Alert popup in PDP");
		}
		
		try{
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			clickObject(PDPPage.addToCartButton);
		}
		catch(Exception e){
			System.out.println("No Alert popup in PDP");
		}
		if(B2CExecution){
			verifyText("Item added to cart", 20);
			verifyText(PDPPage.itemNmaeSecInConfirmPopUp, GlobalObjects.itemName, 10);
		}
		if(B2BExecution){
			int cartCountAfter=0; 
			boolean flag = false;
			for(int i=0;i<30; i++){
				cartCountAfter = getCartCount(); 
				System.out.println("after: "+cartCountAfter);
				if(cartCountAfter>cartCountBefore){
					flag = true;
					break;
				}
				Thread.sleep(1000);
			}
			if(flag)
				result.updateResult(driver, Status.PASS, "Item Added successfully to Cart");
			else
				result.updateResult(driver, Status.FAIL, "Item not Added to Cart");

		}
	}
	
	public void removeSearchedItemFromCart() throws InterruptedException{
		boolean flag = false;
		int cartCountBefore = 0;
		int cartCount = getCartCount();
		cartCountBefore = cartCount;

		try{
		do{
			Thread.sleep(3000);
		clickObject(ShoppingCartPage.removeLink(GlobalObjects.itemName));

		int cartCountAfter=0; 
		flag = false;
		for(int i=0;i<10; i++){
			cartCountAfter = getCartCount(); 
			System.out.println("after: "+cartCountAfter);
			if(cartCountAfter<cartCountBefore){
				flag = true;
				break;
			}
			Thread.sleep(1000);
		}
		
		}while(isElementDisplayed(ShoppingCartPage.removeLink(GlobalObjects.itemName), 5));
		}catch(Exception e){
			
		}
		if(flag && !isTextDisplayed(GlobalObjects.itemName, 5))
			result.updateResult(driver, Status.PASS, "Item removed successfully from Cart");
		else
			result.updateResult(driver, Status.FAIL, "Item not removed from Cart");

	}

	public int getCartCount(){
		String cartCount = getObjText(GlobalObjects.cartCount());
		cartCount = cartCount.replace("(", "");
		cartCount = cartCount.replace(")", "");
		int cartValue = Integer.parseInt(cartCount);
		return cartValue;
	}

	public void addToCartNCheckout() throws InterruptedException{
		isPageLoadingSuccessfull();
		addToCart();
		proceedToCheckout();
		//		if(B2CExecution)
		//			clickObject(PDPPage.proceedToCheckoutButton);
		//		if(B2BExecution){
		//			clickObject(GlobalObjects.cartCount());
		//		}
	}

	public void proceedToCheckout() throws InterruptedException{
		if(B2CExecution)
			clickObject(PDPPage.proceedToCheckoutButton);
		if(B2BExecution)
			clickObject(GlobalObjects.cartCount());
		isPageLoadingSuccessfull();
	}
	
	public void navigateToCartGlobalHeader() throws InterruptedException{
		scrollToElement(GlobalObjects.cartCount());
		clickObject(GlobalObjects.cartCount());
		if(B2CExecution){
			Thread.sleep(2000);
			clickObject(GlobalObjects.viewShoppingCart());
		}
		isPageLoadingSuccessfull();
		verifyElementExistance("Shopping Cart page", ShoppingCartPage.shoppingCartPage(), 20);
	}

	public void continueShoppingCart() throws InterruptedException{
		isPageLoadingSuccessfull();
		scrollToElement(ShoppingCartPage.continueButton);
		clickObject(ShoppingCartPage.continueButton);
		isPageLoadingSuccessfull();
		clickObject(ShoppingCartPage.AchievedpromotionnextButton);
		Thread.sleep(2000);
		clickObject(ShoppingCartPage.nextButton);
		isPageLoadingSuccessfull();
	}

	public void populateShippingAddressCheckout() throws InterruptedException, AWTException{

		boolean newAddress = false;
		boolean existingAddress = false;
		for(int j=0; j<20; j++){
			if(B2BExecution){
				if(isElementDisplayed(ShippingBillingAddressPage.shipToNewAddressLink, 1)){
					existingAddress=true;
					break;
				}
				if(isElementDisplayed(ShippingBillingAddressPage.address1ShippingTextBox, 1)){
					newAddress=true;
					break;
				}
			}
		}
		if(existingAddress){
			clickObject(ShippingBillingAddressPage.shipToNewAddressLink);
			Thread.sleep(2000);
		}
		sendInput(ShippingBillingAddressPage.firstNameTextBox, "Bittu");
		sendInput(ShippingBillingAddressPage.lastNameTextBox, "Shill");
		if(B2BExecution)
			sendInput(ShippingBillingAddressPage.coShippingTextBox, "Business");




		for(int a=3; a<8;a++){
			sendInput(ShippingBillingAddressPage.address1ShippingTextBox, dataTable.getData("TestData", "Address1"));
			for(int i=0; i<10; i++){
				boolean flag = isElementDisplayed(ShippingBillingAddressPage.address1SuggestionList, 10);
				if(flag)
					break;
				else
					Thread.sleep(1000);
			}
			//		if(!isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 10))
			//			Thread.sleep(3000);
			for(int b=0;b<a+1;b++)
				pressKey(KeyEvent.VK_DOWN);
			pressKey(KeyEvent.VK_ENTER);

			Thread.sleep(2000);



			if(isElementDisplayed(ShippingBillingAddressPage.address2SuggestionList, 2)){
				int number = driver.findElements(ShippingBillingAddressPage.address2SuggestionList).size();
				for(int x=0; x<number; x++){
					for(int y=0; y<x+1; y++)
						pressKey(KeyEvent.VK_DOWN);
					pressKey(KeyEvent.VK_ENTER);
					if(!getTextFromTextBox(ShippingBillingAddressPage.address2ShippingTextBox).equalsIgnoreCase(""))
						break;
				}

				Thread.sleep(2000);
			}

			String city = getTextFromTextBox(ShippingBillingAddressPage.cityShippingTextBox);
			System.out.println("City: " +city);
			String zip = getTextFromTextBox(ShippingBillingAddressPage.zipShippingTextBox);
			System.out.println("Zip: " +zip);
			result.updateResult(driver, Status.INFO, "Entrerd information");
			if(!city.equalsIgnoreCase("") && !zip.equalsIgnoreCase(""))
				break;
		}
	}
	
	public void addnewbillinginformation() throws InterruptedException{
		scrollToElement(PaymentInfoPage.Addnewbillinginformation);
		clickObject(PaymentInfoPage.Addnewbillinginformation);
		isPageLoadingSuccessfull();
	}

	public void populateShipAddrNProceedCheckout() throws InterruptedException, AWTException{

		populateShippingAddressCheckout();
		if(FunctionRepository.B2BExecution){
		scrollToElement(ShippingBillingAddressPage.nextButton());
		clickObject(ShippingBillingAddressPage.nextButton());
		}
		if(FunctionRepository.B2CExecution){
			scrollToElement(ShippingBillingAddressPage.nextButtonb2c);
		clickObject(ShippingBillingAddressPage.nextButtonb2c);
		}
		isPageLoadingSuccessfull();
	}

	public void populateBillingAddressCheckout() throws InterruptedException, AWTException{

		boolean registration = false;
		boolean purchase = false;
		driver.switchTo().frame("xiFrameHosted").switchTo().frame("xieCommFrame");
		for(int i=0; i<20; i++){
			if(isElementDisplayed(JoinJafraConsultantPage.creditcardNumberTextBox, 1)){
				registration=true;
				break;
			}
			if(isElementDisplayed(PaymentInfoPage.cardNumber,1)){
				purchase=true;
				break;
			}
			
		}
		driver.switchTo().defaultContent();
		
		
		if(registration || FunctionRepository.B2CExecution){
			sendInput(ShippingBillingAddressPage.address1ShippingTextBox, dataTable.getData("TestData", "Address1"));
			sendInput(ShippingBillingAddressPage.firstNameTextBox, "Bittu");
			sendInput(ShippingBillingAddressPage.lastNameTextBox, "Shill");
			sendInput(ShippingBillingAddressPage.address2ShippingTextBox, dataTable.getData("TestData", "Address2"));
			sendInput(ShippingBillingAddressPage.cityShippingTextBox, dataTable.getData("TestData", "Address2"));
			sendInput(ShippingBillingAddressPage.zipShippingTextBox, dataTable.getData("TestData", "Zip"));
			selectFromWebList(ShippingBillingAddressPage.stateShippingDropDown, dataTable.getData("TestData", "Address2"));
		}
		if(purchase && FunctionRepository.B2BExecution){
			sendInput(PaymentInfoPage.address1BillingTextBox, dataTable.getData("TestData", "Address1"));
			sendInput(PaymentInfoPage.firstNameTextBox, "Bittu");
			sendInput(PaymentInfoPage.lastNameTextBox, "Shill");
			sendInput(PaymentInfoPage.address2BillingTextBox, dataTable.getData("TestData", "Address1"));
			sendInput(PaymentInfoPage.cityBillingTextBox, dataTable.getData("TestData", "Address1"));
			sendInput(PaymentInfoPage.zipBillingTextBox, dataTable.getData("TestData", "Zip"));
			selectFromWebList(PaymentInfoPage.stateBillingDropDown(), dataTable.getData("TestData", "Address1"));
		}
		result.updateResult(driver, Status.INFO, "Entrerd information");
	}

	public void selectPaymentOption1() throws InterruptedException{
		boolean newPayment = false;
		boolean existingPayment = false;
		clickObject(ShippingBillingAddressPage.paymentOption1);
		clickObject(ShippingBillingAddressPage.nextButton());
		verifyPage("Payment Information", 20);

		for(int j=0; j<40; j++){
			if(isElementDisplayed(PaymentInfoPage.addNewPayment,1)){
				existingPayment=true;
				break;
			}
			if(isElementDisplayed(PaymentInfoPage.cardNumber,1)){
				newPayment=true;
				break;
			}
		}

		if(existingPayment){
			clickObject(PaymentInfoPage.addNewPayment);
			Thread.sleep(2000);
		}
		result.updateResult(driver, Status.INFO, "Payment page");
	}

	public void selectPaymentOption2() throws InterruptedException{
		clickObject(ShippingBillingAddressPage.paymentOption2);
		clickObject(ShippingBillingAddressPage.nextButton());
		isPageLoadingSuccessfull();
		verifyPage("Final Review", 20);
	}

	public void selectPaymentOption4() throws InterruptedException{
		clickObject(ShippingBillingAddressPage.paymentOption4);
		clickObject(ShippingBillingAddressPage.nextButton());
		isPageLoadingSuccessfull();
		verifyPage("Final Review", 20);
	}

	public void populateBillCardInfoNProceedCheckout() throws InterruptedException, AWTException{
                                               
		
	
		populatePaymentInfo();
		if(B2BExecution)
			enterPaymentAmount();
		populateBillingAddressCheckout();
		if(FunctionRepository.B2BExecution){
		//clickVisibleObject(ShippingBillingAddressPage.nextButton(), PaymentInfoPage.continueButton);
			//Thread.sleep(5);
		clickVisibleObject(ShippingBillingAddressPage.nextButton(), PaymentInfoPage.savepaymentb2b);
		Thread.sleep(5);
		isPageLoadingSuccessfull();
		}
		 if(FunctionRepository.B2CExecution){
			scrollToElement(ShippingBillingAddressPage.savePaymentb2c);
			clickObject(ShippingBillingAddressPage.savePaymentb2c);
			isPageLoadingSuccessfull();
			 
		}
		
	}

	
	public double getCartTotal(){
		String cartTotal = getObjText(PaymentInfoPage.cartTotal);
		cartTotal = cartTotal.replace("$", "");
		double total = Double.parseDouble(cartTotal);
		return total;
	}

	public void enterPaymentAmount() throws InterruptedException{
		verifyPage("Payment Information", 20);
		double cartTotal = getCartTotal();
		sendInput(PaymentInfoPage.makePaymentTextBox, cartTotal+"");
	}
	public void PopulateNextButton() throws InterruptedException{
		if(FunctionRepository.B2CExecution){
			isElementDisplayed(PaymentInfoPage.NextButton, 15);
			scrollToElement(PaymentInfoPage.NextButton);
			clickObject(PaymentInfoPage.NextButton);
			
			
		}
	}
	
	public void Paymentcontinuebutton() throws InterruptedException{
		isPageLoadingSuccessfull();
			isElementDisplayed(PaymentInfoPage.paymentContinueButton, 15);
			clickObject(PaymentInfoPage.paymentContinueButton);
			
		
	}

	public void reviewNSubmit() throws InterruptedException{
		isPageLoadingSuccessfull();
		
		clickObject(ReviewPage.submitButton);
	
		if(!isPageLoadingSuccessfull())
			driver.switchTo().alert().accept();
		//		isPageLoadingSuccessfull();
        clickObject(ReviewPage.returnToOrderButton);
		isPageLoadingSuccessfull();
	}

	public void verifyReviewNPlaceOrderPage(){
		if(driver.findElements(ReviewNPlaceOrder.placeOrderButton()).size()>0)
			result.updateResult(driver, Status.PASS, "Review and Place order page is displayed");
		else
			result.updateResult(driver, Status.FAIL, "Review and Place order page is not displayed");
	}
	


	public void placeOrder() throws InterruptedException{
		if(B2CExecution){
			scrollToElement(ReviewNPlaceOrder.termsConditionsCheckbox);
			clickObject(ReviewNPlaceOrder.termsConditionsCheckbox);
			scrollToElement(ReviewNPlaceOrder.placeOrderButton());
			clickObject(ReviewNPlaceOrder.placeOrderButton());
		}
		if(B2BExecution){
			scrollToElement(ReviewPage.placeOrderButton);
			clickObject(ReviewPage.placeOrderButton);
		}
	}

	public void verifyOrderPlacedSuccesfully() throws InterruptedException{
		if(B2CExecution)
			verifyPage("Order Confirmation", 30);
		if(B2BExecution)
			verifyPage("Confirmation", 30);
	}
	
	public void joinJafraConsultant() throws InterruptedException{
		clickObject(MyAccSignInPage.becomeAConsultant);
		verifyElementExistance("Consultant Join Now page", JoinJafraConsultantPage.joinJafraImage, 10);
		populateJoinJafraContactInfo();
		clickObject(JoinJafraConsultantPage.submit);
		verifyPage("Consultant's Information", 30);
		clickObject(JoinJafraConsultantPage.next);
		
		
		
		isPageLoadingSuccessfull();
	}
	
	public void verifyJoinJafraPage() throws InterruptedException{
		verifyElementExistance("Consultant Join Now page", JoinJafraConsultantPage.joinJafraImage, 10);
	}
	
	public void populateAndSubmitJoinJafraContactInfo() throws InterruptedException{
		populateJoinJafraContactInfo();
		clickObject(JoinJafraConsultantPage.submit);
	}
	
	public void verifyConsultantInformationPage() throws InterruptedException{
		verifyPage("Consultant's Information", 30);
	}
	
	public void verifyAndProceedThroughConsInfoPage() throws InterruptedException{
		verifyConsultantInformationPage();
		clickObject(JoinJafraConsultantPage.next);
	}
	
	public void selectStarterkit() throws InterruptedException{
		verifyElementExistance("Enabled select starter kit ", JoinJafraConsultantPage.selectStarterKitLabel, 10);
		clickObject(JoinJafraConsultantPage.starterKit);
		clickObject(JoinJafraConsultantPage.next);
		isPageLoadingSuccessfull();
	}
	
	public void enterShippingAndPaymentInfo() throws InterruptedException, AWTException{
		verifyElementExistance("Enabled enter shipping ", JoinJafraConsultantPage.enterShippingLabel, 10);
		populateJoinJafraShippingAddress();
		clickObject(JoinJafraConsultantPage.next);
		verifyElementExistance("Enabled payment information ", JoinJafraConsultantPage.paymentInfoLabel, 10);
		populatePaymentInfo();
		//clickObject(JoinJafraConsultantPage.Mybillingshippingaddressarethesame);
		//clickObject(JoinJafraConsultantPage.next);
		submitObject(JoinJafraConsultantPage.next);
		
		
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(JoinJafraConsultantPage.nextpaymetricbutton));
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click;", JoinJafraConsultantPage.nextpaymetricbutton);
		//driver.findElement(By.id("payment_information_submit")).click();
		isPageLoadingSuccessfull();
	}
	 
	
	public void navigateToBusinessWebsite() throws InterruptedException{
		verifyElementExistance("Enabled business website ", JoinJafraConsultantPage.businessWebsiteLabel, 10);
		scrollToElement(JoinJafraConsultantPage.next);
		clickObject(JoinJafraConsultantPage.next);
		isPageLoadingSuccessfull();
	}
	
	public void qualifyAsAConsultant() throws InterruptedException, AWTException{
		verifyElementExistance("Enabled qualify as a consultant ", JoinJafraConsultantPage.qualifyAsConsultantLabel, 10);
		scrollToElement(JoinJafraConsultantPage.noThanks);
		clickObject(JoinJafraConsultantPage.noThanks);
		if(isElementDisplayed(JoinJafraConsultantPage.areYouSurePopUp, 15))
			clickObject(JoinJafraConsultantPage.iAmSureButton);
		pressKey(KeyEvent.VK_END);
		isPageLoadingSuccessfull();
		Thread.sleep(10);
		scrollToElement(JoinJafraConsultantPage.nothank);
		clickObject(JoinJafraConsultantPage.nothank);
		Thread.sleep(20);
		isPageLoadingSuccessfull();
		
		scrollToElement(JoinJafraConsultantPage.nextbutton);
		clickObject(JoinJafraConsultantPage.nextbutton);
		isPageLoadingSuccessfull();
		clickObject(JoinJafraConsultantPage.achievdpromotionnextbutton);
		isPageLoadingSuccessfull();
		
		
	}
	
	
	public void acceptAgreement() throws InterruptedException{

		scrollToElement(JoinJafraConsultantPage.agreementCheckbox);
		clickObject(JoinJafraConsultantPage.agreementCheckbox);
		clickObject(JoinJafraConsultantPage.agreementButton);
	}

	public void reviewNConfirm() throws InterruptedException{
		verifyElementExistance("Enabled review and confirm ", JoinJafraConsultantPage.reviewNConfirmLabel, 10);
		isPageLoadingSuccessfull();
	}
	
	public void enterSSNDOB() throws InterruptedException{
		sendInput(JoinJafraConsultantPage.sSNTextBox, commonFunctions.genRandomNumberString(9));
		sendInput(JoinJafraConsultantPage.dOBTextBox, "01/01/1990");
		result.updateResult(driver, Status.INFO, "Entrerd information");
	}
	public void placeOrderNJoinJafra() throws InterruptedException, AWTException{
		
		clickObject(JoinJafraConsultantPage.placeOrderButton);
		isElementDisplayed(GlobalObjects.loadingIcon, 5);
		isPageLoadingSuccessfull();
		populatePrimaryGoalPassword();
		clickObject(JoinJafraConsultantPage.JoinButton);
		verifyElementExistance("B2B registration confirmation page - Welcome to Jafra ", JoinJafraConsultantPage.welcomeToJafra, 30);
		Thread.sleep(1000);
	}



	public String getSponserDetails(String detailType){
		String id = "";
		String name = "";
		if(detailType.equalsIgnoreCase("id")){
			id = getObjText(JoinJafraConsultantPage.sponsorId);
			return id;
		}
		if(detailType.equalsIgnoreCase("name")){
			name = getObjText(JoinJafraConsultantPage.sponsorName);
			return name;
		}
		return null;
	}
	public void skincaresection() throws InterruptedException{
		clickObject(GlobalObjects.skin);
		verifyElementExistance("Skin, transformed displayed", GlobalObjects.skincare, 30);
		scrollToElement(GlobalObjects.BUYNOW);
		clickObject(GlobalObjects.BUYNOW);
		isPageLoadingSuccessfull();
		clickObject(GlobalObjects.ADDTOCART);
		isPageLoadingSuccessfull();
		//clickObject(GlobalObjects.ADDTOCART);
		Thread.sleep(300);
		//proceedToCheckout();
		clickObject(GlobalObjects.proceedtochecout);
		isPageLoadingSuccessfull();
		//clickObject(GlobalObjects.shippingaddressNext);
		//isPageLoadingSuccessfull();
		//clickObject(GlobalObjects.paymentinformationnext);
		//isPageLoadingSuccessfull();
		scrollToElement(ReviewNPlaceOrder.termsConditionsCheckbox);
		clickObject(ReviewNPlaceOrder.termsConditionsCheckbox);
		scrollToElement(ReviewNPlaceOrder.placeOrderButton());
		clickObject(ReviewNPlaceOrder.placeOrderButton());
		isPageLoadingSuccessfull();
		
		
	
    
	}
	
	public void populateJoinJafraContactInfo() throws InterruptedException{
		String fName = "Bittu";
		GlobalObjects.fName=fName;
		String lName = "Shill";
		GlobalObjects.lName=lName;
		sendInput(JoinJafraConsultantPage.firstNameTextBox, fName);
		sendInput(JoinJafraConsultantPage.lastNameTextBox, lName);
		sendInput(JoinJafraConsultantPage.eMailTextBox, genRandomAlphaString(10)+"@cognizant.com");
		//		sendInput(JoinJafraConsultantPage.eMailTextBox, dataTable.getData("TestData", "Email"));
		String phoneNumber = "541754"+genRandomNumberString(4);
//		sendInput(JoinJafraConsultantPage.phoneNumberTextBox, dataTable.getData("TestData", "PhoneNumber"));
		sendInput(JoinJafraConsultantPage.phoneNumberTextBox, phoneNumber);
		selectFromWebList(JoinJafraConsultantPage.phoneTypeDropDown, "Work Phone");
		selectFromWebList(JoinJafraConsultantPage.preferredContactMethodDropDown, "Email");
		result.updateResult(driver, Status.INFO, "Entrerd information");
	}

	public void populateJoinJafraShippingAddress() throws InterruptedException, AWTException{
		//		if(!GlobalObjects.fName.equalsIgnoreCase(commonFunctions.
		//				getTextFromTextBox(JoinJafraConsultantPage.fNameShippingTextBox)))
		//				result.updateResult(driver, Status.FAIL, "First name is not prepopulated");
		//		if(!GlobalObjects.lName.equalsIgnoreCase(commonFunctions.
		//				getTextFromTextBox(JoinJafraConsultantPage.lNameShippingTextBox)))
		//			result.updateResult(driver, Status.FAIL, "Last name is not prepopulated");
		sendInput(JoinJafraConsultantPage.businessCOShippingTextBox, "Business");		

		//		sendInput(JoinJafraConsultantPage.address1ShippingTextBox, dataTable.getData("TestData", "Address1"));
		//		for(int i=0; i<10; i++){
		//			boolean flag = isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 10);
		//			if(flag)
		//				break;
		//		}
		//		//		if(!isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 10))
		//		//			Thread.sleep(3000);
		//		pressKey(KeyEvent.VK_DOWN);
		//		pressKey(KeyEvent.VK_ENTER);
		//		Thread.sleep(1000);
		//		//		sendInput(JoinJafraConsultantPage.address2ShippingTextBox, "");
		//		//		sendInput(JoinJafraConsultantPage.cityShippingTextBox, "");
		//		//		selectFromWebList(JoinJafraConsultantPage.stateShippingTextBox, "");
		//		//		sendInput(JoinJafraConsultantPage.zipShippingTextBox, "");
		//		result.updateResult(driver, Status.INFO, "Entrerd information");










		for(int a=0; a<5;a++){
			sendInput(JoinJafraConsultantPage.address1ShippingTextBox, dataTable.getData("TestData", "Address1"));
			for(int i=0; i<10; i++){

				boolean flag = isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 10);
				if(flag)
					break;
				else
					Thread.sleep(1000);
			}
			//		if(!isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 10))
			//			Thread.sleep(3000);
			for(int b=0;b<a+1;b++)
				pressKey(KeyEvent.VK_DOWN);
			pressKey(KeyEvent.VK_ENTER);

			Thread.sleep(2000);



			if(isElementDisplayed(JoinJafraConsultantPage.address1SuggestionList, 2)){
				int number = driver.findElements(JoinJafraConsultantPage.address1SuggestionList).size();
				for(int x=0; x<number; x++){
					for(int y=0; y<x+1; y++)
						pressKey(KeyEvent.VK_DOWN);
					pressKey(KeyEvent.VK_ENTER);
					if(!getTextFromTextBox(JoinJafraConsultantPage.address1ShippingTextBox).equalsIgnoreCase(""))
						break;
				}

				Thread.sleep(2000);
			}

			String city = getTextFromTextBox(JoinJafraConsultantPage.cityShippingTextBox);
			System.out.println("City: " +city);
			String zip = getTextFromTextBox(JoinJafraConsultantPage.zipShippingTextBox);
			System.out.println("Zip: " +zip);
			result.updateResult(driver, Status.INFO, "Entrerd information");
			if(!city.equalsIgnoreCase("") && !zip.equalsIgnoreCase(""))
				break;
		}
	}

	public void populatePaymentInfo() throws InterruptedException{
		boolean registration = false;
		boolean purchase = false;
		driver.switchTo().frame("xiFrameHosted").switchTo().frame("xieCommFrame");
//		driver.switchTo().frame("xieCommFrame");
		for(int i=0; i<20; i++){
			if(isElementDisplayed(JoinJafraConsultantPage.creditcardNumberTextBox, 1)){
				registration=true;
				break;
			}
			if(isElementDisplayed(PaymentInfoPage.cardNumber,1)){
				purchase=true;
				break;
			}
		}
		
		if(registration){
			
			//selectFromWebList(JoinJafraConsultantPage.creditcardTypeDropDown, 
					//dataTable.getData("TestData", "CardType"));
			sendInput(JoinJafraConsultantPage.creditcardNameTextBox, "Name");

			sendInput(JoinJafraConsultantPage.creditcardNumberTextBox, 
					dataTable.getData("TestData", "CardNumber"));
			selectFromWebList(JoinJafraConsultantPage.creditcardExpMonthDropDown, 
					dataTable.getData("TestData", "ExpMonth"));
			selectFromWebList(JoinJafraConsultantPage.creditcardExpYearDropDown, 
					dataTable.getData("TestData", "ExpYear"));
			sendInput(JoinJafraConsultantPage.creditcardSecurityCodeTextBox, 
					dataTable.getData("TestData", "SecurityCode"));
			
			
		}
		if(purchase){

			//selectFromWebList(PaymentInfoPage.cardType, 
					//dataTable.getData("TestData", "CardType"));
			sendInput(PaymentInfoPage.nameOnCard, "Name");

			sendInput(PaymentInfoPage.cardNumber, 
					dataTable.getData("TestData", "CardNumber"));
			selectFromWebList(PaymentInfoPage.expMoth, 
					dataTable.getData("TestData", "ExpMonth"));
			selectFromWebList(PaymentInfoPage.expYear, 
					dataTable.getData("TestData", "ExpYear"));
			sendInput(PaymentInfoPage.cvvNumber, 
					dataTable.getData("TestData", "SecurityCode"));

		
		}
		driver.switchTo().defaultContent();
		  
		result.updateResult(driver, Status.INFO, "Entrerd information");
	
	}

	public void populatePrimaryGoalPassword() throws InterruptedException{
		isElementDisplayed(JoinJafraConsultantPage.JoinButton, 15);
		selectFromWebList(JoinJafraConsultantPage.goalDropDown, "Work for myself");
		sendInput(JoinJafraConsultantPage.passwordTextBox, "b212345678");
		sendInput(JoinJafraConsultantPage.conPasswordTextBox, "b212345678");

		result.updateResult(driver, Status.INFO, "Entrerd information");
	}

	public void navigateToContactUsPage() throws InterruptedException{
		clickObject(GlobalObjects.contactUsLink());
		verifyElementExistance("Contact Us page", ContactUsPage.contactUsPage(), 20);
	}

	public void verifyConsultantCarePage() throws InterruptedException{
		verifyText("Email Us", 5);
		verifyText("Send New", 5);
		verifyText("See All Responses", 5);
		verifyText("Chat With Us", 5);
		verifyText("Chat With a Live Representative", 5);
		verifyText("Hours of Operation\n8:00 AM - 6:00 PM Pacific Time", 5);
		verifyText("Call Us", 5);
		verifyText("1-800-852-3728", 5);
		verifyText("Hours of Operation\n8:00 AM - 6:00 PM Pacific Time", 5);
	}

	public void populateContactUsInformation() throws InterruptedException{
		selectFromWebList(ContactUsPage.topicDropDown(), "I have a problem with this website");
		sendInput(ContactUsPage.firstNameTextBox(), "Bittu");
		sendInput(ContactUsPage.lastNameTextBox(), "Shill");
		sendInput(ContactUsPage.eMailTextBox(), "bittushill@cognizant.com");
		sendInput(ContactUsPage.phoneTextBox(), "541-754-3010");
		sendInput(ContactUsPage.messageTextBox(), "I have a problem with this website");
		result.updateResult(driver, Status.INFO, "Entrerd information");
	}

	
	public void submitContactUsFrom() throws InterruptedException{
		scrollToElement(ContactUsPage.submitButton());
		clickObject(ContactUsPage.submitButton());
		isPageLoadingSuccessfull();

	}
	public void verifyContactUsSubmission() throws InterruptedException{
		verifyText("Thank you for contacting us! We've successfully received your submission. "
				+ "We will respond as soon as we are able to your request.Please note that your ticket ID is:", 20);
	}
	public void navigateToMyAccGeneralPage() throws InterruptedException{
		clickObject(MyAccountPage.generalLink);
		verifyElementExistance("General page", MyAccountPage.generalPage(), 20);
	}
	public void navigateToMyAccShippingMailingPage() throws InterruptedException{
		clickObject(MyAccountPage.shippingMailingLink);
		verifyElementExistance("Shipping Mailing page", MyAccountPage.shippingMailingPage(), 20);
	}
	public void navigateToMyAccPaymentBillingPage() throws InterruptedException{
		clickObject(MyAccountPage.paymentBillingLink);
		verifyElementExistance("Payment Billing page", MyAccountPage.paymentBillingPage(), 20);
	}
	public void navigateToMyAccPreferencesPage() throws InterruptedException{
		clickObject(MyAccountPage.preferencesLink);
		verifyElementExistance("Preferences page", MyAccountPage.preferencesPage(), 20);
	}
	public void navigateToMyAccWebsiteManagementPage() throws InterruptedException{
		clickObject(MyAccountPage.websiteManagementLink);
		verifyElementExistance("Website Management page", MyAccountPage.websiteManagementPage(), 20);
	}
	public void navigateToMyAccAccountActivityPage() throws InterruptedException{
		clickObject(MyAccountPage.accountActivityLink);
		verifyElementExistance("Account Activity page", MyAccountPage.accountActivityPage(), 20);
	}
	public void navigateToMyAccOrderHistoryPage() throws InterruptedException{
		clickObject(MyAccountPage.orderHistoryLink);
		if(B2BExecution)
			verifyElementExistance("Order History page", MyAccountPage.myOrdersPage(), 20);
		if(B2CExecution)
			verifyElementExistance("Order History page", MyAccountPage.orderHistoryPage(), 20);
	}
	public void navigateToMyAccPaymentHistoryPage() throws InterruptedException{
		clickObject(MyAccountPage.paymentHistoryLink);
		verifyElementExistance("Payment History page", MyAccountPage.paymentHistoryPage(), 20);
	}

	public void navigateToMyAccSignInInfoPage() throws InterruptedException{
		clickObject(MyAccountPage.signInInfoLink);
		verifyElementExistance("Sign In Info page", MyAccountPage.signInInfoPage(), 20);
	}
	public void navigateToMyAccBillingInfoPage() throws InterruptedException{
		clickObject(MyAccountPage.billilngInfoLink);
		verifyElementExistance("Billilng Info page", MyAccountPage.billingInfoPage(), 20);
	}
	public void navigateToMyAccShippingAddPage() throws InterruptedException{
		clickObject(MyAccountPage.shippingAddLink);
		verifyElementExistance("Shipping Address page", MyAccountPage.shippingAddressPage(), 20);
	}

	public void navigateToMyAccMyWishListPage() throws InterruptedException{
		clickObject(MyAccountPage.myWishListLink);
		verifyElementExistance("My Wishlist page", MyAccountPage.myWishlistPage(), 20);
	}
	public void navigateToMyAccMyReviewsPage() throws InterruptedException{
		clickObject(MyAccountPage.myReviewsLink);
		verifyElementExistance("My Reviews page", MyAccountPage.myReviewsPage(), 20);
	}

	public void verifyMyAccPages() throws InterruptedException{
		if(B2BExecution){
			navigateToMyAccGeneralPage();
			navigateToMyAccShippingMailingPage();
			navigateToMyAccPaymentBillingPage();
			navigateToMyAccPreferencesPage();
			navigateToMyAccWebsiteManagementPage();
			navigateToMyAccAccountActivityPage();
			navigateToMyAccOrderHistoryPage();
			navigateToMyAccPaymentHistoryPage();
		}
		if(B2CExecution){
			navigateToMyAccSignInInfoPage();
			navigateToMyAccBillingInfoPage();
			navigateToMyAccShippingAddPage();
			navigateToMyAccOrderHistoryPage();
			navigateToMyAccMyWishListPage();
			navigateToMyAccMyReviewsPage();
		}
	}

	public void addItemToWishList() throws InterruptedException{
		GlobalObjects.itemName = getItemNameFromPDP();
		scrollToElement(PDPPage.addToWishlistLink);
		clickObject(PDPPage.addToWishlistLink);
		isPageLoadingSuccessfull();
	}

	public void verifyItemInWishList() throws InterruptedException{
		verifyText(GlobalObjects.itemName, 20);
	}

	public void removeWishListItem() throws InterruptedException{
		boolean flag = false;
		clickObject(MyAccountPage.wishListRemoveButton);
		for(int i=0; i<30; i++){
			if(!isTextDisplayed(GlobalObjects.itemName, 1))
				flag = true;
			else
				Thread.sleep(1000);
		}
		if(flag)
			result.updateResult(driver, Status.PASS, "Item removed successfully from Wish List");
		else
			result.updateResult(driver, Status.FAIL, "Item still present in Wish List");
	}
	
	public void clearCart() throws InterruptedException{
		int beforeCount=0;
		int afterCount=0;
		while(driver.findElements(ShoppingCartPage.removeLinkButton()).size()>0){
			beforeCount=driver.findElements(ShoppingCartPage.removeLinkButton()).size();
			clickObject(ShoppingCartPage.removeLinkButton());
			for(int i=0; i<10; i++){
				try{
				afterCount=driver.findElements(ShoppingCartPage.removeLinkButton()).size();
				if(afterCount<beforeCount)
					break;
				else
					Thread.sleep(1000);
				}catch(Exception e){
					Thread.sleep(1000);
				}
			}
		}
	}

	public void verifyHomePageCoponents() throws InterruptedException{
		verifyMegaMenuOptions();
		verifyFooterLinks();
		verifySocialMediaIcons();

		verifyElementExistance("Slider Images", HomePage.sliderImages, 5);
		if(B2CExecution){
			verifyElementExistance("ImageHold Images", HomePage.imageHold, 5);
			verifyElementExistance("VideoHold Images", HomePage.videoHold, 1);
		}
		if(B2BExecution){
			verifyElementExistance("Featured Product section", HomePage.featuredProduct, 5);
			verifyElementExistance("Calander Events section", HomePage.calanderOfEvents, 1);
			verifyElementExistance("Month to Date progress", HomePage.monthToDateProgress, 1);
			verifyElementExistance("My Jafra Story", HomePage.myJafraStory, 1);
			verifyElementExistance("Progress Statistics", HomePage.progressStatistics, 1);
			verifyElementExistance("What's Happening", HomePage.whatsHappening, 1);
		}
	}

	public void verifyMegaMenuOptions() throws InterruptedException{
		if(B2CExecution){
			verifyElementExistance("Skin Care menu", GlobalObjects.category(GlobalObjects.skinCare), 5);
			verifyElementExistance("Rragrance menu", GlobalObjects.category(GlobalObjects.fragrance), 1);
			verifyElementExistance("Make Up menu", GlobalObjects.category(GlobalObjects.makeUp), 1);
			verifyElementExistance("Bath and Body menu", GlobalObjects.category(GlobalObjects.bathNBody), 1);
			verifyElementExistance("E Catalog menu", GlobalObjects.category(GlobalObjects.eCatalog), 1);
			verifyElementExistance("Beauty Scene menu", GlobalObjects.category(GlobalObjects.beautyScene), 1);
			verifyElementExistance("Special Offers menu", GlobalObjects.category(GlobalObjects.specialOffers), 1);
			verifyElementExistance("Become A Consultant menu", GlobalObjects.category(GlobalObjects.becomeAConsultant), 1);
		}
		if(B2BExecution){
			verifyElementExistance("Shop menu", GlobalObjects.category(GlobalObjects.shop), 5);
			verifyElementExistance("My Business menu", GlobalObjects.category(GlobalObjects.myBusiness), 1);
			verifyElementExistance("Whats Happening menu", GlobalObjects.category(GlobalObjects.whatsHappening), 1);
			verifyElementExistance("Sponsoring menu", GlobalObjects.category(GlobalObjects.sponsoring), 1);
		}
	}

	public void verifyFooterLinks() throws InterruptedException{
		if(B2CExecution){
			verifyElementExistance("Contact Us footer link", GlobalObjects.links(GlobalObjects.contactUs), 5);
			verifyElementExistance("Choose Country footer link", GlobalObjects.links(GlobalObjects.chooseCountry), 1);
			verifyElementExistance("About Jafra footer link", GlobalObjects.links(GlobalObjects.aboutJafra), 1);
			verifyElementExistance("Client Care footer link", GlobalObjects.links(GlobalObjects.clientCare), 1);
			verifyElementExistance("DSA footer link", GlobalObjects.links(GlobalObjects.dSA), 1);
			verifyElementExistance("Join Jafra footer link", GlobalObjects.links(GlobalObjects.joinJafra), 1);
		}
		if(B2BExecution){

			verifyElementExistance("Consultant Care footer link", GlobalObjects.links(GlobalObjects.consultantCare), 5);
			verifyElementExistance("+About Jafra footer link", GlobalObjects.links(GlobalObjects.aboutJafraPlus), 1);
			verifyElementExistance("+DSA footer link", GlobalObjects.links(GlobalObjects.dSAPlus), 1);
			verifyElementExistance("Terms footer link", GlobalObjects.links(GlobalObjects.terms), 1);
			verifyElementExistance("Policies and Procedures footer link", GlobalObjects.links(GlobalObjects.policiesNProcedures), 1);
			verifyElementExistance("Privacy footer link", GlobalObjects.links(GlobalObjects.privacy), 1);

		}
	}

	public void verifySocialMediaIcons() throws InterruptedException{
		verifyElementExistance("Facebook Icon", GlobalObjects.faceBookIcon(), 5);
		verifyElementExistance("Youtube Icon", GlobalObjects.youtubeIcon(), 1);
		verifyElementExistance("Instagram Icon", GlobalObjects.instagramIcon(), 1);
		verifyElementExistance("Pinterest Icon", GlobalObjects.pinterestIcon(), 1);
		verifyElementExistance("Twitter Icon", GlobalObjects.twitterIcon(), 1);

	}

	public void navigateToAddToOrderPage() throws InterruptedException{
		mouseHover(GlobalObjects.category(GlobalObjects.myBusiness));
	    clickObject(GlobalObjects.subCategory("Shop by Item Code"));
		//clickObject(GlobalObjects.subCategory("Make a Payment"));
		isPageLoadingSuccessfull();
		verifyElementExistance("Add To Order page", AddToOrderPage.addToOrderPage, 20);
	}

	public void addItemToOrder() throws InterruptedException{
		int index=0;
		boolean flag = false;
		sendInput(AddToOrderPage.itemNumberTextBox(index), dataTable.getData("TestData", "ItemNumber"));
		clickObject(AddToOrderPage.quantityTextBox(index));
		for(int i=0; i<10; i++){
			if(isElementDisplayed(AddToOrderPage.removeLink(index), 1)){
				flag=true;
				break;
			}
			else
				Thread.sleep(1000);
			System.out.println(i);
		}
		if(flag){
			if(! dataTable.getData("TestData", "Quantity").equalsIgnoreCase(""))
				sendInput(AddToOrderPage.quantityTextBox(index), dataTable.getData("TestData", "Quantity"));
			else
				sendInput(AddToOrderPage.quantityTextBox(index), "1");
			result.updateResult(driver, Status.INFO, "Entrerd information");

		}
		else
			result.updateResult(driver, Status.FAIL, "Item row is not displayed in the Add to Order page");
		clickObject(AddToOrderPage.itemNumberTextBox(index));
		Thread.sleep(1000);


	}
	
	public void addItemToDeal() throws InterruptedException{

		int index=0;
		boolean flag = false;
		sendInput(AddToOrderPage.itemNumberTextBox(index), dataTable.getData("TestData", "ItemNumber"));
		clickObject(AddToOrderPage.quantityTextBox(index));
		for(int i=0; i<10; i++){
			if(isElementDisplayed(AddToOrderPage.removeLink(index), 1)){
				flag=true;
				break;
			}
			else
				Thread.sleep(1000);
			System.out.println(i);
		}
		
		Thread.sleep(1000);


	
	}

	public void clickAddToCartInOrderPage() throws InterruptedException{
		scrollToElement(AddToOrderPage.addToCartButton);
		clickObject(AddToOrderPage.addToCartButton);
		isPageLoadingSuccessfull();
	}
	
	public void quantityUpdate() throws InterruptedException{
		//sendInput(ShoppingCartPage.quantityTextBox, dataTable.getData("TestData", "Quantity"));
		sendInput(ShoppingCartPage.quantityTextBox, "2");
		clickObject(ShoppingCartPage.viewMoreLink);
		scrollToElement(ShoppingCartPage.updateCartButton);
		clickObject(ShoppingCartPage.updateCartButton);
	}

	public void setWebsiteOn() throws InterruptedException{
		if(driver.findElement(MyAccountPage.websiteOn).getAttribute("class").toString().contains("btn on active btn-primary"));
		else
			clickObject(MyAccountPage.websiteOn);
		clickObject(MyAccountPage.save);
	}

	public void getPersonalWebsiteURL(){
		GlobalObjects.personalWebsiteURL="";
		String url = driver.findElement(MyAccountPage.websiteURL).getAttribute("href").toString();
		GlobalObjects.personalWebsiteURL = url;
	}

	public void openPersonalWebsite(){
		driver.get(GlobalObjects.personalWebsiteURL);
		String consultantName = GlobalObjects.personalWebsiteURL.substring(GlobalObjects.personalWebsiteURL.lastIndexOf("/")+2);
		if(getObjText(GlobalObjects.consultantName).toLowerCase().contains(consultantName.toLowerCase()))
			result.updateResult(driver, Status.PASS, "Personal Website is open successfully");
		else
			result.updateResult(driver, Status.FAIL, "Personal Website is not open");
	}

	public void navigatToSpecialOfferPage() throws InterruptedException{
		if(B2BExecution){
			mouseHover(GlobalObjects.shopMenu());
		}
		clickObject(GlobalObjects.category(GlobalObjects.specialOffers));
		isPageLoadingSuccessfull();
		verifyPLPPage("Special Offers", 20);
	}

//	public void addToCartPLPDeal() throws InterruptedException{
//		int cartCountBefore = 0;
//		int cartCount = getCartCount();
//		cartCountBefore = cartCount;
//		System.out.println("before: "+cartCountBefore);
//
//		if(B2BExecution){
//			int number = getRandNumFromMultiExistance(PLPPage.addToCartButton());
//
//			scrollToElement(PLPPage.addToCartButton(1));
//			clickObject(PLPPage.addToCartButton(1));
//		}
//		if(B2CExecution){
//			int number = getRandNumFromMultiExistance(PLPPage.buyNowButton());
//
//			scrollToElement(PLPPage.buyNowButton(1));
//			clickObject(PLPPage.buyNowButton(1));
//			isPageLoadingSuccessfull();
//			GlobalObjects.itemName = "";
//			GlobalObjects.itemName = getItemNameFromPDP();
//			scrollToElement(PDPPage.addToCartButton);
//			clickObject(PDPPage.addToCartButton);
//			if(isElementDisplayed(PDPPage.selectReplenishment, 20)){
//				selectFromWebList(PDPPage.selectReplenishment, "Every 60 days");
//				clickObject(PDPPage.saveToCart);
//			}
//		}
//		if(isElementDisplayed(PLPPage.dealDescription(), 8)){
//			for(int i=1; i<=getNumberOfExistance(PLPPage.dealDescription()); i++){
//				clickObject(PLPPage.dealDescription(i));
//				try{
//					System.out.println(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString());
//					if(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString().contains("true"));
//
//					else{
//						clickObject(PLPPage.addToCartMyOffer());
//						break;
//					}
//				}catch(Exception e){
//					clickObject(PLPPage.addToCartMyOffer());
//					break;
//				}
//			}
//			isPageLoadingSuccessfull();
//		}
//
//		if(B2CExecution){
//			verifyText("Item added to cart", 20);
//			verifyText(PDPPage.itemNmaeSecInConfirmPopUp, GlobalObjects.itemName, 10);
//		}
//		if(B2BExecution){
//			int cartCountAfter=0; 
//			boolean flag = false;
//			for(int i=0;i<10; i++){
//				cartCountAfter = getCartCount(); 
//				System.out.println("after: "+cartCountAfter);
//				if(cartCountAfter>cartCountBefore){
//					flag = true;
//					break;
//				}
//				Thread.sleep(1000);
//			}
//			if(flag)
//				result.updateResult(driver, Status.PASS, "Item Added successfully to Cart");
//			else
//				result.updateResult(driver, Status.FAIL, "Item not Added to Cart");
//
//		}
//	}
	
	public void addToCartPLPDeal() throws InterruptedException{
		int cartCountBefore = 0;
		int cartCount = getCartCount();
		cartCountBefore = cartCount;
		System.out.println("before: "+cartCountBefore);
		boolean flag1 = false;
		if(B2BExecution){
			int number = getRandNumFromMultiExistance(PLPPage.addToCartButton());
			GlobalObjects.itemName = getObjText(PLPPage.itemName(1));
			scrollToElement(PLPPage.addToCartButton(1));
			clickObject(PLPPage.addToCartButton(6));
		}
		if(B2CExecution){
			int number = getRandNumFromMultiExistance(PLPPage.buyNowButton());

			scrollToElement(PLPPage.buyNowButton(11));
			clickObject(PLPPage.buyNowButton(11));
			isPageLoadingSuccessfull();
			GlobalObjects.itemName = "";
			GlobalObjects.itemName = getItemNameFromPDP();
			scrollToElement(PDPPage.addToCartButton);
			clickObject(PDPPage.addToCartButton);
			if(isElementDisplayed(PDPPage.selectReplenishment, 20)){
				selectFromWebList(PDPPage.selectReplenishment, "Every 60 days");
				clickObject(PDPPage.saveToCart);
			}
		}
		if(isElementDisplayed(PLPPage.dealDescription(), 8)){
			int limit = 5;
			for(int i=0; i<limit; i++){
//				Random rand = new Random();
//				int number = rand.nextInt(getNumberOfExistance(PLPPage.dealDescription()));
				
				
				i = clickObject(PLPPage.dealDescription(), i);
				limit = i+5;
				
				
				try{
					System.out.println(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString());
					if(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString().contains("true")){
						System.out.println("Add to Cart is not actvated yet");
					}

					else{
						clickObject(PLPPage.addToCartMyOffer());
						break;
					}
				}catch(Exception e){
					clickObject(PLPPage.addToCartMyOffer());
					break;
				}
			}
			flag1=true;
			isPageLoadingSuccessfull();
		}

		if(!flag1 && B2CExecution){
			isPageLoadingSuccessfull();
			verifyText("Item added to cart", 20);
			verifyText(PDPPage.itemNmaeSecInConfirmPopUp, GlobalObjects.itemName, 10);
			proceedToCheckout();
		}
		if(B2BExecution || flag1){
			int cartCountAfter=0; 
			boolean flag = false;
			for(int i=0;i<10; i++){
				cartCountAfter = getCartCount(); 
				System.out.println("after: "+cartCountAfter);
				if(cartCountAfter>cartCountBefore){
					flag = true;
					break;
				}
				Thread.sleep(1000);
			}
			if(flag)
				result.updateResult(driver, Status.PASS, "Item Added successfully to Cart");
			else
				result.updateResult(driver, Status.FAIL, "Item not Added to Cart");
			if(flag1)
			{
				
				navigateToCartGlobalHeader();
				scrollToElement(ShoppingCartPage.checkoutButton);
				clickObject(ShoppingCartPage.checkoutButton);
				isPageLoadingSuccessfull();
			}

		}
	}
	
	public void addItemToCartDeal() throws InterruptedException{
		int cartCountBefore = 0;
		int cartCount = getCartCount();
		cartCountBefore = cartCount;
		System.out.println("before: "+cartCountBefore);
		boolean flag1 = false;
		if(isElementDisplayed(PLPPage.dealDescription(), 8)){
			int limit = 5;
			for(int i=0; i<limit; i++){
//				Random rand = new Random();
//				int number = rand.nextInt(getNumberOfExistance(PLPPage.dealDescription()));
				
				
				i = clickObject(PLPPage.dealDescription(), i);
				limit = i+5;
				
				
				try{
					System.out.println(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString());
					if(driver.findElement(PLPPage.addToCartMyOffer()).getAttribute("disabled").toString().contains("true")){
						System.out.println("Add to Cart is not actvated yet");
					}

					else{
						clickObject(PLPPage.addToCartMyOffer());
						break;
					}
				}catch(Exception e){
					clickObject(PLPPage.addToCartMyOffer());
					break;
				}
			}
			flag1=true;
			isPageLoadingSuccessfull();
		}

		if(!flag1 && B2CExecution){
			isPageLoadingSuccessfull();
			verifyText("Item added to cart", 20);
			verifyText(PDPPage.itemNmaeSecInConfirmPopUp, GlobalObjects.itemName, 10);
			proceedToCheckout();
		}
		if(B2BExecution || flag1){
			
			String itemName = driver.findElement(ShoppingCartPage.firstAddItemName).getText();
			GlobalObjects.itemName = itemName;
			
			clickAddToCartInOrderPage();
			int cartCountAfter=0; 
			boolean flag = false;
			for(int i=0;i<10; i++){
				cartCountAfter = getCartCount(); 
				System.out.println("after: "+cartCountAfter);
				if(cartCountAfter>cartCountBefore){
					flag = true;
					break;
				}
				Thread.sleep(1000);
			}
			if(flag)
				result.updateResult(driver, Status.PASS, "Item Added successfully to Cart");
			else
				result.updateResult(driver, Status.FAIL, "Item not Added to Cart");
//			if(flag1)
//			{
//				
//				navigateToCartGlobalHeader();
//				scrollToElement(ShoppingCartPage.checkoutButton);
//				clickObject(ShoppingCartPage.checkoutButton);
//				isPageLoadingSuccessfull();
//			}

		}
	}
	
	public void verifyMyOrderHeaderContent() throws InterruptedException{
		isElementDisplayed(MyAccountPage.orderElement(), 20);
		isElementDisplayed(MyAccountPage.dateElement(), 20);
		isElementDisplayed(MyAccountPage.statusElement(), 20);
	}
	
	public void verifyOrderDetailsData(){
		
	}
	
	public void navigateToSponsoringPage() throws InterruptedException{
		clickObject(GlobalObjects.sponsoringLink);
		verifyJoinJafraPage();
	}
	
	

	public void loginToServiceNow() throws InterruptedException{
		sendInput(ServiceNow.userNameTextBox, dataTable.getData("TestData", "UserId"));
		sendInput(ServiceNow.passwordTextBox, dataTable.getData("TestData", "Password"));
		clickObject(ServiceNow.loginButton);
		driver.switchTo().frame("gsft_main");
		verifyElementExistance("Logged In page", ServiceNow.loggedInPage, 20);
		driver.switchTo().defaultContent();
	}

	public void navigateToCreateNewChangeRequestPage() throws InterruptedException{
		clickObject(ServiceNow.changeCreateNewLink);
		driver.switchTo().frame("gsft_main");
		verifyElementExistance("Change Request page", ServiceNow.changeRequestPage, 20);
		driver.switchTo().defaultContent();
	}
	
	public void verifyPrivacypage() throws InterruptedException {
		scrollToElement(HomePage.clickonprivacylink);
		clickObject(HomePage.clickonprivacylink);
		isPageLoadingSuccessfull();
		

        ArrayList<String>s= new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(s.get(1));
		verifyElementExistance("JAFRA WEBSITE PRIVACY POLICY",PrivacyPage.JAFRAPRIVACYPOLICY,10);
	}
	
	public void shippingandmailing() throws InterruptedException{
		  clickObject(MyAccountPage.shippingMailingLink);
		  isPageLoadingSuccessfull();
		  clickObject(MyAccountPage.editbutton);
		  isPageLoadingSuccessfull();
		  driver.findElement(MyAccountPage.Firstname).clear();
		  verifyElementExistance("Firstname", MyAccountPage.Firstname, 20);
		  driver.findElement(MyAccountPage.Lastname).clear();
		  verifyElementExistance("Lastname", MyAccountPage.Lastname, 20);
		  driver.findElement(MyAccountPage.Business).clear();
		  verifyElementExistance("Business", MyAccountPage.Business, 20);
		  driver.findElements(MyAccountPage.address1).clear();
		  verifyElementExistance("address1", MyAccountPage.address1, 20);
		  //driver.findElement(MyAccountPage.address2).clear();
		  //Thread.sleep(200);
		  driver.findElement(MyAccountPage.city).clear();
		  verifyElementExistance("city", MyAccountPage.city, 20);
		 // driver.findElement(MyAccountPage.state).clear();
		  //Thread.sleep(200);
		  driver.findElement(MyAccountPage.zipcode).clear();
		  verifyElementExistance("zipcode", MyAccountPage.zipcode, 20);
		  
		  sendInput(MyAccountPage.Firstname, "AMIT");
		  verifyElementExistance("Firstname", MyAccountPage.Firstname, 20);

		  sendInput(MyAccountPage.Lastname, "DDDDD");
		  verifyElementExistance("Lastname", MyAccountPage.Lastname, 20);
		  
		  sendInput(MyAccountPage.Business, "PARTNERSHIP");
		  verifyElementExistance("Business", MyAccountPage.Business, 20);

		  sendInput(MyAccountPage.address1, "850 Gov Carlos G Camacho Rd");
		  verifyElementExistance("address1", MyAccountPage.address1, 20);
		  
		  sendInput(MyAccountPage.address2, "Po Box");
		  verifyElementExistance("address2", MyAccountPage.address2, 20);
		  sendInput(MyAccountPage.city, "Tamuning");
		  verifyElementExistance("city", MyAccountPage.city, 20);  
		 Select state = new Select(driver.findElement(By.id("shippingAddrState")));
		state.selectByVisibleText("Guam");
		verifyElementExistance("state", MyAccountPage.state, 20);
		sendInput(MyAccountPage.zipcode, "969133128");
		verifyElementExistance("zipcode", MyAccountPage.zipcode, 20);
		  clickObject(MyAccountPage.savebuttonshippingmethod);
		  isPageLoadingSuccessfull();
		  verifyElementExistance("editbutton", MyAccountPage.editbutton, 20);
		  Thread.sleep(2000);
		  
		 
		
		
	}
	public void shippingaddress() throws InterruptedException{
		clickObject(MyAccountPage.b2cshippingaddress);
		  verifyElementExistance("b2cshippingaddress", MyAccountPage.b2cshippingaddress, 20);
		  isPageLoadingSuccessfull();
		  clickObject(MyAccountPage.ADDNEW);
		  isPageLoadingSuccessfull();
		  verifyElementExistance("ADDNEW", MyAccountPage.ADDNEW, 20);
		  sendInput(MyAccountPage.B2CFirstname, "DEVANSHU");
		  verifyElementExistance("B2CFirstname", MyAccountPage.B2CFirstname, 20);
		  sendInput(MyAccountPage.B2CADDRESS1, "122 New York Ave");
		  verifyElementExistance("B2CADDRESS1", MyAccountPage.B2CADDRESS1, 20);

		 // sendInput(MyAccountPage.B2CADDRESS2, "HUWAI");
		  //verifyElementExistance("B2CADDRESS2", MyAccountPage.B2CADDRESS2, 20);

		  sendInput(MyAccountPage.B2CCITY, "Gadsden");
		  verifyElementExistance("B2CCITY", MyAccountPage.B2CCITY, 20);
		  Select B2Cstate = new Select(driver.findElement(By.id("shippingAddrState")));
			B2Cstate.selectByVisibleText("Alabama");
			  verifyElementExistance("B2CSTATE", MyAccountPage.B2CSTATE, 20);
			  sendInput(MyAccountPage.B2Czipcode, "359033854");
			  verifyElementExistance("B2Czipcode", MyAccountPage.B2Czipcode, 20);
			  sendInput(MyAccountPage.b2cphoneno,"5417543010");
			  verifyElementExistance("b2cphonenoe",MyAccountPage.b2cphoneno,20);
			  clickObject(MyAccountPage.B2Csaveaddress);
			  isPageLoadingSuccessfull();
			  verifyElementExistance("b2cedit", MyAccountPage.b2cedit, 20);
			  
			  
			  
			  
			  


		  
          

		  

		  

	}
	
	//public void nextbutton()throws InterruptedException{
		//clickObject(JoinJafraConsultantPage.nextpaymetricbutton);
		
	//}

	

	public void attachItem() throws InterruptedException{
		driver.switchTo().frame("gsft_main");
		clickObject(ServiceNow.attachLink);

		WebElement popup = driver.findElement(By.id("attachment"));
		WebElement chooseFile = popup.findElement(ServiceNow.chooseFile);
		chooseFile.sendKeys(dataTable.getData("TestData", "FilePath"));
		clickObject(ServiceNow.attachButton);
		if(isElementDisplayed(ServiceNow.removeButton, 60))
			clickObject(ServiceNow.popUpCross);
		else
			result.updateResult(driver, Status.FAIL, "Problem in attaching the file");
		//driver.switchTo().defaultContent();
		
	}
	
	
	
}
