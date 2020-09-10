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

		for (int i = 1; i < len; i++) {
			min = Integer.parseInt(fares.get(i - 1).getText().replaceAll(",", ""));
			max = Integer.parseInt(fares.get(i).getText().replaceAll(",", ""));
			
			if (max < min) {
				int temp = min;
				min = max;
				max = temp;
			}
		}

		return min;
	}

	public int findShortestFlightDuration() {

		int len = duration.size();
		int maxD, minD = 0;

		for (int i = 0; i < len; i++) {
			minD = Integer.parseInt(duration.get(i).toString());
			maxD = Integer.parseInt(duration.get(i++).toString());
			if (maxD < minD) {
				int temp = minD;
				minD = maxD;
				maxD = temp;
			}
		}

		return minD;
	}

	public flightReviewPage selectLowFareFlight() {

		turnPriceToggleUp();

		System.out.println("Cheapest fare of among all Flight = ₹" + findCheapFlightFare());
		System.out.println("Shortest duration among all flights = " + duration.get(0).getText());
		bookBtn.get(0).click();

		return new flightReviewPage();

	}

}
