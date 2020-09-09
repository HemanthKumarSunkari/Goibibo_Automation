package com.goibibo.pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.goibibo.base.TestBase;

public class flightReviewPage extends TestBase {

	Scanner scanner_user;
	Select select;

	public flightReviewPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[contains(text(),'I am willing to risk my trip')]")
	WebElement noInsuranceBtn;

	@FindBy(id = "Adulttitle1")
	WebElement Title;

	@FindBy(id = "AdultfirstName1")
	WebElement firstName;

	@FindBy(id = "AdultmiddleName1")
	WebElement middleName;

	@FindBy(id = "AdultlastName1")
	WebElement lastName;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "mobile")
	WebElement mobile;

	@FindBy(xpath = "//button[contains(@class,'button')]//div[1]")
	WebElement proceedBtn;
	
	@FindBy(xpath = "//span[@class='bg-greyLt quicks fb white brdrRd10 ico15 padLR20 padTB8 curPointer']")
	WebElement skipToPaymentBtn;

	@FindBy(id = "tab_paypal")
	WebElement paypal;
	
	@FindBy(xpath = "//label[contains(@for, 'radio0INR')]") 
	WebElement inrAmount;

	public boolean enterPassengerDetails() {

		
		js.executeScript("arguments[0].scrollIntoView();", noInsuranceBtn);
		Wait(noInsuranceBtn).click();
		
		js.executeScript("arguments[0].scrollIntoView();", proceedBtn);
			
		System.out.println("Entered Passenger details are : ");
		Title.sendKeys(prop.getProperty("pTitle"));
		System.out.println("Title = " + prop.getProperty("pTitle"));
		
		firstName.sendKeys(prop.getProperty("pFName"));
		System.out.println("FirstName = " + prop.getProperty("pFName"));
		
		middleName.sendKeys(prop.getProperty("pMName"));
		System.out.println("MiddleName = " + prop.getProperty("pMName"));
		
		lastName.sendKeys(prop.getProperty("pLName"));
		System.out.println("LastName = " + prop.getProperty("pLName"));
	
		email.sendKeys(prop.getProperty("PEmail"));
		System.out.println("Email = " + prop.getProperty("PEmail"));
		
		mobile.sendKeys(prop.getProperty("pMobile"));
		System.out.println("Mobile = " + prop.getProperty("pMobile"));
		
		
		proceedBtn.click();
		
		try{
			//driver.switchTo().alert().accept();
			driver.findElement(By.xpath("//button[@class='button blue large fb padLR30']")).click();
				
			js.executeScript("arguments[0].scrollIntoView();", skipToPaymentBtn);
			skipToPaymentBtn.click();
		}catch(Exception e) {
			System.out.println("There is no option to select seats and SkipToPayment button");
		}
		
		js.executeScript("arguments[0].scrollIntoView();", skipToPaymentBtn);
		Wait(paypal);
		paypal.click();
		
		return inrAmount.isEnabled();
		
	}
	

}
