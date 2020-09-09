package com.goibibo.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.goibibo.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;	
	public static long EXPLICIT_WAIT = 20;
	
	
	public static void waitt() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	
	public static void captureScreenshot() throws IOException {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String currentDir = System.getProperty("user.dir");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_MM_SS_a");
		String date = sdf.format(d);
		
		String path = currentDir + "\\Screenshot\\" + date +".png";
		
		FileUtils.copyFile(scrFile, new File(path));
		
	}

	
	
}
