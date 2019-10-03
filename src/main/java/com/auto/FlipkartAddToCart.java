package com.auto;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;


//Test code to add a product to flipkart cart
public class FlipkartAddToCart {
	
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
	public void logIn() {
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("8085676882");
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("password");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();	
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
}
