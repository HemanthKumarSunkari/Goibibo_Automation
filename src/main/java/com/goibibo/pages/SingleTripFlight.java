package com.goibibo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.goibibo.base.TestBase;

public class SingleTripFlight extends TestBase {

	public SingleTripFlight() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"PRICE\"]/span/i")
	WebElement price;

	@FindBy(xpath = "//span[contains(@class,'ico20') and contains(@class, 'quicks') and contains(@class, 'fb')]")
	List<WebElement> fares;

	@FindBy(xpath = "//div[contains(@data-cy, 'duration') and contains(@class, 'quicks')]")
	List<WebElement> duration;

	@FindBy(xpath = "//span[contains(@data-cy, 'depTime') and contains(@class, 'quicks')]")
	List<WebElement> departureTime;

	@FindBy(xpath = "//*[contains(@data-cy,'bookBtn')]")
	List<WebElement> bookBtn;

	
	/********  Action Methods  *******/
	int index = 0;
	public void turnPriceToggleUp() {
		String clName = price.getAttribute("class");
		if (!clName.contains("icon-arrow2-up")) {
			System.out.println("Intially , Prices are starting from highest fare value");
			price.click();
			System.out.println("Now , Prices are going to start with lowest fare value");
		}
		System.out.println("Prices are started from least fare value");
	}

	public int findCheapFlightFare() {
		int len = fares.size();
		int min = 0, max = 0;
		min = Integer.parseInt(fares.get(0).getText().replaceAll(",", ""));
		for (int i = 1; i < len; i++) {
			
			max = Integer.parseInt(fares.get(i).getText().replaceAll(",", ""));
			
			if (max < min) {
				min = max;
				index = i;
			}
		}

		return min;
	}

	public String findShortestFlightDuration() {

		int len = duration.size();
		int maxD, minD = 0;
		minD = Integer.parseInt(duration.get(0).getText().replaceAll(" ", "").replaceAll("h", "").replaceAll("m", ""));
				
		for (int i = 1; i < len; i++) {
			maxD = Integer.parseInt(duration.get(i).getText().replaceAll(" ", "").replaceAll("h", "").replaceAll("m", ""));
			if (maxD < minD) {
				minD = maxD;
			}
		}
		
		int hour = minD/100;
		int minute = minD - (hour*100);
		return hour+"hr "+minute+"min";
	}

	public flightReviewPage selectLowFareFlight() {

		turnPriceToggleUp();
		

		System.out.println("Cheapest fare of among all Flight = ₹"+ findCheapFlightFare());
		System.out.println("Shortest duration among all flights = " + findShortestFlightDuration());
		System.out.println("Index of selected flight = " + index);
		bookBtn.get(index).click();

		return new flightReviewPage();

	}

}
