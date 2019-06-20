package com.cognizant.pom.EcomPages;

import org.openqa.selenium.By;

public class HomePage {
	public static By sliderImages = By.xpath("//div[@class='cycle-gallery gallery-js-ready autorotation-active']//img");
	public static By imageHold = By.xpath("//div[@class='img-hold']//img");
	public static By videoHold = By.xpath("//div[@class='video-hold']//img");
	public static By featuredProduct = By.xpath("//h3[contains(text(),\"JAFRA'S FEATURED PRODUCTS\")]//following-sibling::img");
	public static By calanderOfEvents = By.xpath("//div[@class='wrapper calendar']");
	public static By monthToDateProgress = By.xpath("//h3[contains(text(),'MONTH TO DATE PROGRESS')]");
	public static By progressStatistics = By.xpath("//div[@id='BI-us-statistics']");
	public static By myJafraStory = By.xpath("//h3[contains(text(),'MY JAFRA STORY')]");
	public static By whatsHappening = By.xpath("//div[@class='showcase news']");
	public static By clickonprivacylink=By.xpath("//a[contains(text(),'PRIVACY')]");
}
