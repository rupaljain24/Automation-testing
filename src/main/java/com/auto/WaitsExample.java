package com.auto;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WaitsExample {
	


WebDriver driver;
	
	@Test(priority = 1)
	public void browser() {
		String path = System.getProperty("user.dir");
		String genericPath = path + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", genericPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	@Test(priority = 2)
	public void logIn() throws IOException {
		driver.get(GetConfig.getData("Environment"));
		
		// This is another way to find the html element to pass some data in it.
		WebElement element = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		element.sendKeys("8085676882");
		
		
		Actions action = new Actions(driver);
		//This is to write capital letter in any input box.
		
		
		
		
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("password");
		
		
		WebElement elementClick = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
		Action build = action.click(elementClick).build();
		build.perform();
		
		
		
	}
}
