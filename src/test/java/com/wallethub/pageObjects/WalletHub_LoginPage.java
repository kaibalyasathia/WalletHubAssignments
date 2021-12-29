package com.wallethub.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.SeleniumUtils;

/**
 * 
 * @author Kaibalya
 *
 *This class will store all the locators and methods of WalletHub's Review page
 *
 */
public class WalletHub_LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//span[text()='Login']")
	WebElement loginLink;
	
	@FindBy(xpath="//input[@name='em']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='pw']")
	WebElement passwrd;
	
	@FindBy(xpath="//span[text()='Login']")
	WebElement loginButton;
	
	public WalletHub_LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void  clickLoginLink() {	
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(loginLink));
		SeleniumUtils.click(loginLink);
	}
	
	public void  setUserName(String user) {	
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(email));
		SeleniumUtils.sendKeys(email,user);
		
	}
	
	public void  setPassword(String user) {	
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwrd));
		SeleniumUtils.sendKeys(passwrd,user);
		
	}
	public void  clickLoginButton() {	
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		SeleniumUtils.click(loginButton);
	}
	
}
