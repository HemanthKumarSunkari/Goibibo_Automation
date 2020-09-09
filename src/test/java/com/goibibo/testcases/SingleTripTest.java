package com.goibibo.testcases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.goibibo.base.TestBase;
import com.goibibo.pages.LandingPage;
import com.goibibo.pages.SingleTripFlight;
import com.goibibo.pages.flightReviewPage;

public class SingleTripTest extends TestBase{

	@Test()
	public static void SingleTripPaymentTest() throws ParseException {
		LandingPage lp = new LandingPage();

		Assert.assertEquals(lp.validateFlightTab(), true, "Fligt tab is not selected");
		Assert.assertEquals(lp.selectSingleTripTab(), true, "OneWay tab is not selected");
		
		SingleTripFlight sf = lp.enterSingleTripDetails();
		
		flightReviewPage fr = sf.selectLowFareFlight();
		
		Assert.assertEquals(fr.enterPassengerDetails(), true, "Payment amount is other than INR");
		
	}

}
