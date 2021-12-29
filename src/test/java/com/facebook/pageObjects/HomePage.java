package com.facebook.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
WebDriver driver;
WebDriverWait wait;

	@FindBy(xpath = "//textarea[@name='xc_message']")
	WebElement postTextArea;
		
	@FindBy(xpath = "//input[@value='Post']")
	WebElement postButton;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void setPostText(String text) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(postTextArea));
		SeleniumUtils.sendKeys(postTextArea,text);
	}
	public void clickPost() {
		wait=new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(postTextArea));
		SeleniumUtils.click(postButton);
	}
	


}
