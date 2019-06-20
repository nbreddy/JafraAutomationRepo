package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class JoinJafraConsultantPage {
	public static By joinJafraImage = By.cssSelector("div[id$=WhyJafraHeader_whyJafra]"
			+ "[class=page-heading]");
	public static By firstNameTextBox = By.name("firstName");
	public static By lastNameTextBox = By.name("lastName");
	public static By eMailTextBox = By.name("email");
	public static By phoneNumberTextBox = By.name("phone");
	
	public static By phoneTypeDropDown = By.id("typeOfPhone");
	public static By preferredContactMethodDropDown = By.id("contactMeVia");
	
	
	public static By submit = By.cssSelector("input[value=Submit]");
	public static By next = By.cssSelector("input[value=Next]");
	//public static By nextpaymetricbutton=By.xpath("//input[@class=' btn btn-red ']");
	//public static By nextt = By.cssSelector("//input[@id='payment_information_submit']");
	public static By noThanks = By.xpath("//button[contains(text(),'No Thanks')]");
	public static By nothank=By.xpath("//button[@id='add_fav_no_thanks']");
	public static By nextbutton=By.xpath("//a[@id='btnNextShoppingCartB2C']");
	public static By Mybillingshippingaddressarethesame=By.xpath("//input[@class='payment_information_class save_shipping_address_class']");
	
	
	//public static By mayBeLater=By.xpath("//button[@id='websiste_maybelater']");
	public static By areYouSurePopUp = By.xpath("//h4[text()='Are you sure?']");
	public static By iAmSureButton = By.xpath("//button[text()=\"I'M Sure\"]");
	public static By agreementCheckbox = By.xpath("//input[@name='acknowledge']");
	public static By agreementButton = By.xpath("//button[@name='agree']");
	public static By sSNTextBox = By.xpath("//input[@name='socialSecurityNumber']");
	public static By dOBTextBox = By.xpath("//input[@name='dateOfBirth']");
	public static By placeOrderButton = By.xpath("//input[@id='place_order_submit']");
	
	public static By goalDropDown = By.xpath("//select[@id='primaryGoal']");
	public static By passwordTextBox = By.xpath("//input[@id='review_modal_pass_id']");
	public static By conPasswordTextBox = By.xpath("//input[@id='review_modal_pass_confirm_id']");
	public static By JoinButton = By.xpath("//input[@id='place_order_goal_submit']");
	public static By welcomeToJafra = By.xpath("//div[contains(text(),'Welcome to JAFRA!')]");
	public static By sponsorId = By.name("sponsorId");
	public static By sponsorName = By.name("name");
	public static By selectStarterKitLabel = By.xpath("//h3[contains(text(),'Select Starter Kit')][@aria-selected='true']");
	public static By starterKit = By.xpath("//div[@class='kit']");
	public static By enterShippingLabel = By.xpath("//h3[contains(text(),'Enter Shipping')][@aria-selected='true']");
	
	public static By paymentInfoLabel = By.xpath("//h3[contains(text(),'Payment Information')][@aria-selected='true']");
	public static By businessWebsiteLabel = By.xpath("//h3[contains(text(),'Jafra Business Website')][@aria-selected='true']");
	public static By businessWebsiteName = By.xpath("//input[@id='website_site_name']");
	
	public static By qualifyAsConsultantLabel = By.xpath("//h3[contains(text(),'Qualify as a Consultant Today!')][@aria-selected='true']");
	public static By reviewNConfirmLabel = By.xpath("//h3[contains(text(),'Review & Confirm')][@aria-selected='true']");
	public static By fNameShippingTextBox = By.xpath("//input[@id='shipping_firstname_id']");
	public static By lNameShippingTextBox = By.id("shipping_lastname_id");
	public static By businessCOShippingTextBox = By.id("shipping_business_co_id");
	public static By address1ShippingTextBox = By.id("shipping_address_line1_id");
	public static By address2ShippingTextBox = By.id("shipping_address_line2_id");
	public static By cityShippingTextBox = By.id("shipping_address_city_id");
	public static By stateShippingTextBox = By.id("shipping_state_id");
	public static By zipShippingTextBox = By.id("shipping_zip_id");
	public static By address1SuggestionList = By.className("ui-menu-item");
	public static By achievdpromotionnextbutton = By.id("btnNextShoppingCartB2C");
	
	
	public static By creditcardNameTextBox = By.id("credit-card-name-id");
	//public static By creditcardTypeDropDown = By.id("credit-card-type-id");
	public static By creditcardNumberTextBox = By.id("credit-card-number-id");
	public static By creditcardExpMonthDropDown = By.id("credit-card-month-id");
	public static By creditcardExpYearDropDown = By.id("credit-card-year-id");
	public static By creditcardSecurityCodeTextBox = By.id("credit-card-code-id");
	
	
}
