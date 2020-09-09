package com.goibibo.testcases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.goibibo.base.TestBase;
import com.goibibo.pages.LandingPage;
import com.goibibo.pages.RoundTripFlights;
import com.goibibo.pages.flightReviewPage;


public class RoundTripTest extends TestBase{
	
	
	@Test()
	public void RoundTripPaymentTest() throws ParseException, InterruptedException {
		LandingPage lp = new LandingPage();
		RoundTripFlights rf = new RoundTripFlights();
		flightReviewPage fr = new flightReviewPage();
		
		
		lp.enterRoundTripDetails();
		rf.selectHighPriceFlight();
		Assert.assertEquals(fr.enterPassengerDetails(), true);
	}

	
	
}
