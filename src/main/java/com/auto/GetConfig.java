package com.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

/* DESCRIPTION
 * -----------------------------
 * Demo practice,
 * To fetch data,
 * from config.properties file.
 * -----------------------------
*/
public class GetConfig {
	
	@Test
	public static String getData(String key) throws IOException {
	FileInputStream fis = new FileInputStream(new File("C:\\Users\\rupaljain\\eclipse-workspace\\AutomationPractice\\config.properties"));
	
	Properties prop = new Properties();
	prop.load(fis);
	String result = prop.getProperty(key);
	return result;
	}
	
}
