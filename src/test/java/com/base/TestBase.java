package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties pro;
	
	public  WebDriver initialization() {

		File file=new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver=new ChromeDriver(option);		
		return driver;
				
	}
	
	public Properties configSetup() {
		
		pro=new Properties();
		File config=new File(getClass().getClassLoader().getResource("Config.properties").getFile());
		FileInputStream fi;
		try {
			fi = new FileInputStream(config);
			pro.load(fi);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return pro;
	}
	

	
	public void failed(String testName) {
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/Screenshots/"+testName+".jpg"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
}
