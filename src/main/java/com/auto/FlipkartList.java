package com.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

/*
 * Demo Practice to add multiple product to flipkart cart using Excel sheet and config.properties file,Actions class and Action interface  
 */
public class FlipkartList {
	
WebDriver driver;
	
		public XSSFWorkbook accessExcel() throws FileNotFoundException, IOException {
			FileInputStream fis = new FileInputStream(new File(GetConfig.getData("ExcelSheet")));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			return workbook;
		}

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
		public void logIn() throws IOException, InterruptedException {
			driver.get(GetConfig.getData("Environment"));
			
			Thread.sleep(5000);
			
			XSSFWorkbook workbook = accessExcel();
			XSSFSheet sheet = workbook.getSheet(GetConfig.getData("credential"));
			Iterator<Row> it = sheet.iterator();
			Row row = it.next();
			
			Thread.sleep(5000);
			// This is another way to find the html element to pass some data in it.
			WebElement element = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
			element.clear();
			element.sendKeys(String.valueOf((long)row.getCell(0).getNumericCellValue()));
			
			
			Actions action = new Actions(driver);
			//This is to write capital letter in any input box.
			
			/*
			Action build = action.moveToElement(element).keyDown(Keys.SHIFT).sendKeys("abcd").keyUp(Keys.SHIFT).build();
			build.perform();
			*/
			
			driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(row.getCell(1).getStringCellValue());
			
			//This action of clicking is done by Action class see below.
			//driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			WebElement elementClick = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
			Action build = action.click(elementClick).build();
			build.perform();
		}

	
	@Test(priority = 4, enabled = false)
	public void searchProduct(String category) throws FindFailed, InterruptedException, IOException {
		
//		Screen screen = new Screen();
//		Pattern mypattern = new Pattern(GetConfig.getData("SearchImage"));
		//input[@title='Search for products, brands and more']
		WebElement element = driver.findElement(By.xpath("//input[@title='Search for products, brands and more']"));
		element.clear();
		element.sendKeys(category);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	@Test(priority = 5, enabled = false)
	public void identifyProductByTitle(String title) throws FindFailed, InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='"+title+"']")).click();
	}
	
	@Test(priority = 6, enabled = false)
	public void addToCart() throws InterruptedException {

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}
	
	@Test(priority = 3,enabled = true)
	public void identifyProduct() throws IOException, FindFailed, InterruptedException {
		
		XSSFWorkbook workbook = accessExcel();
		XSSFSheet sheet = workbook.getSheet(GetConfig.getData("sheet"));
		
		Iterator<Row> it = sheet.iterator();
		while(it.hasNext()) {
			Row row = it.next();
			searchProduct(row.getCell(0).getStringCellValue());
			identifyProductByTitle(row.getCell(1).getStringCellValue());
			addToCart();
		}
		workbook.close();
	}
}
