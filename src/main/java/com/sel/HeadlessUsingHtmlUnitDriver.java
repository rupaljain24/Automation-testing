package com.sel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class HeadlessUsingHtmlUnitDriver {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void HeadlessChrome_Test_1() {
		
		driver = new HtmlUnitDriver();
		
		driver.get("https://google.com");
		String title = driver.getTitle();
		System.out.println("############"+title+"####################");
		
	}

}
