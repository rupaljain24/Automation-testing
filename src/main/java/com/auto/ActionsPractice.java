package com.auto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class ActionsPractice {

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
		
		/*
		Action build = action.moveToElement(element).keyDown(Keys.SHIFT).sendKeys("abcd").keyUp(Keys.SHIFT).build();
		build.perform();
		*/
		
		
		
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("password");
		
		//This action of clicking is done by Action class see below.
		//driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		WebElement elementClick = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
		Action build = action.click(elementClick).build();
		build.perform();
	}
	
	@Test(priority = 3)
	public void searchProduct() throws FindFailed, InterruptedException {
		
		Screen screen = new Screen();
		Pattern mypattern = new Pattern("C:\\Users\\rupaljain\\Desktop\\Search.png");
		Thread.sleep(3000);
		screen.type(mypattern, "realme 5 pro back cover");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	@Test(priority = 4)
	public void identifyProduct() throws FindFailed, InterruptedException {
		
		Thread.sleep(5000);
		Screen screen = new Screen();
		Pattern mypattern = new Pattern("C:\\Users\\rupaljain\\Desktop\\cover.png");
		screen.click(mypattern);
	}
	
	@Test(priority = 5)
	public void addToCart() throws InterruptedException {
		
		    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(0));
		    driver.close();
		    driver.switchTo().window(tabs2.get(1));
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
	}
	
	/*
	Code to handle javascript pop-ups
	
	@Test(priority = 6)
	public void popup() {
		driver.get("Your page URI");
		driver.findElement(By.xpath("//button")).click();
		
		Alert alert = driver.switchTo().alert();
	    //multiple actions on popup.
		
		alert.accept();
		alert.dismiss();
		alert.getText();
		alert.sendKeys("Hiii");
	}
	
	 */
		
}
