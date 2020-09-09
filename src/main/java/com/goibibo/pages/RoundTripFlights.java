package com.goibibo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.goibibo.base.TestBase;

public class RoundTripFlights extends TestBase {

	public RoundTripFlights() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "PRICE")
	List<WebElement> price;

	@FindBy(xpath = "//div[contains(@class,'fltHpyOnwrdWrp')]//div[contains(@class,'marginB10')]//div[1]//div[1]//div[2]//div[2]//div[1]//span[2]//label[1]")
	WebElement departFlight;

	@FindBy(xpath = "//div[@class='fltHpyRtrnWrp']//div[@class='marginB10']//div[1]//div[1]//div[1]//div[2]//div[1]//span[2]//label[1]")
	WebElement returnFlight;

	@FindBy(xpath = "//input[contains(@class,'button')]")
	WebElement searchBtn;

	public flightReviewPage selectHighPriceFlight() {
		
		js.executeScript("window.scrollBy(0,200)");
		Wait(price.get(0));
		price.get(0).click();
		
		Wait(departFlight);
		departFlight.click();
		
		Wait(price.get(1));		
		price.get(1).click();
		

		
		Wait(departFlight);
		returnFlight.click();
		
		searchBtn.click();

		return new flightReviewPage();
	}
}
