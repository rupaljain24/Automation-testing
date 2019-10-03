package com.sel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class HeadlessChrome {
	WebDriver driver;
	
	@Test(priority = 1)
	public void HeadlessChrome_Test_1() {
		String path = System.getProperty("user.dir");
		String genericChromePath = path+"\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", genericChromePath);
		//---------------------------------------------
		ChromeOptions coptions = new ChromeOptions();
		//coptions.setHeadless(true);
	    coptions.addArguments("--headless");
		driver = new ChromeDriver(coptions);
		//----------------------------------------------
		
		driver.get("https://google.com");
		String title = driver.getTitle();
		System.out.println(title);
	}

}
