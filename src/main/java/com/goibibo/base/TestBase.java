
package com.goibibo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.goibibo.util.TestUtil;
import com.goibibo.util.WebEventListener;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\goibibo\\config\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public static void initialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions(); 
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("safari")) {
			DriverManagerType safari = DriverManagerType.SAFARI;
			WebDriverManager.getInstance(safari).setup();
			Class<?> safariClass =  Class.forName(safari.browserClass());
			driver = (WebDriver) safariClass.getDeclaredConstructor().newInstance();
		}
		 js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener  = new WebEventListener();
		driver = e_driver.register(eventListener);

		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	public static WebElement Wait(WebElement e) {
		wait = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
		return wait.until(ExpectedConditions.visibilityOf(e));

	}
}
