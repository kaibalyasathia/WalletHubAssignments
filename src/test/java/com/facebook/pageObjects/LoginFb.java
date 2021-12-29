package com.facebook.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;
/**
 * 
 * @author Kaibalya
 *
 *This class will store all the locators and methods of login page
 *
 */
public class LoginFb {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(name = "email")
	WebElement email;
	
	@FindBy(name = "pass")
	WebElement passwrd;
	
	@FindBy(xpath="//*[@id=\"root\"]/table/tbody/tr/td/div/div[3]/a")
	WebElement notNowButton;
	
	@FindBy(name = "login")
	WebElement loginButton;
	
	public LoginFb(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void  setUserName(String user) {	
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(email));
		SeleniumUtils.sendKeys(email,user);
	}
	public void  setPassword(String password) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwrd));
		SeleniumUtils.sendKeys(passwrd,password);
	}
	public void  clickOnLoginButton() {
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		SeleniumUtils.click(loginButton);		
	}
	public void  clickOnNotNowButton() {
		if(SeleniumUtils.isDisplayed(notNowButton))	
			wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(notNowButton));
			SeleniumUtils.click(notNowButton);		
	}
	
	
}
