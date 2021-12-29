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
import org.testng.Assert;

import utils.SeleniumUtils;

/**
 * 
 * @author Kaibalya
 *
 *         This class will store all the locators and methods of WalletHub's
 *         Review page
 *
 */
public class WalletHub_ReviewPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a[@class='rat-review link']")
	WebElement reviewsLink;

	@FindBy(xpath = "//h3[@aria-label=\"What's Your Rating ?\"]/parent::div/review-star/div//*[local-name()='svg' ][4]")
	WebElement fourStar;

	@FindBy(xpath = "//div[text()='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "//span[text()='Select...']")
	WebElement selectDropdown;

	@FindBy(xpath = "//span[text()='Select...']/following-sibling::ul/li[text()='Health Insurance']")
	WebElement optionToSelect;

	@FindBy(xpath = "//textarea[@placeholder='Write your review...']")
	WebElement reviewTextarea;

	@FindBy(xpath = "//div[@aria-label='List of more navigations']/following-sibling::div/span")
	WebElement loggedInUser;

	@FindBy(xpath = "//div[@aria-label='List of more navigations']/following-sibling::div/div/a[text()='Profile']")
	WebElement userProfile;

	@FindBy(xpath = "//section[@class='rvtab-content']/article[1]//span[@itemprop='author']")
	WebElement recentReviewBy;

	@FindBy(xpath = "//section[@class='rvtab-content']/article")
	List<WebElement> articles;

	@FindBy(xpath = "//section[@class='rvtab-content']/article[1]")
	WebElement firstArticle;

	@FindBy(xpath = "//*[@id=\"scroller\"]/main/div/div/div[1]/h4")
	WebElement reviewSucessMsg;

	@FindBy(xpath = "//div[text()='Continue']")
	WebElement continueButton;

	@FindBy(xpath = "//a[text()='Test Insurance Company']")
	WebElement myReview;

	public WalletHub_ReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickReviewsLink() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(reviewsLink));
		SeleniumUtils.click(reviewsLink);
	}
	
	public void checkIfReviewed() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(recentReviewBy));
		
		String reviewBy=SeleniumUtils.getText(recentReviewBy);
		
		boolean r_Val = SeleniumUtils.compareStrings(reviewBy, " Your Review");
		Assert.assertFalse(r_Val,
				"You have already given review. Please use a new user.");

	}

	public void hoverOnReviewStars() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(fourStar));

		List<WebElement> beforeHover = driver.findElements(By.xpath(
				"//h3[@aria-label=\"What's Your Rating ?\"]/parent::div/review-star/div//*[local-name()='svg' ][3]/*[local-name()='g']/*[local-name()='path']"));
		System.out.println(beforeHover.size());
		SeleniumUtils.hoverOnElement(driver, fourStar);
		List<WebElement> afterHover = driver.findElements(By.xpath(
				"//h3[@aria-label=\"What's Your Rating ?\"]/parent::div/review-star/div//*[local-name()='svg' ][3]/*[local-name()='g']/*[local-name()='path']"));
		System.out.println(afterHover.size());
		boolean rVal = SeleniumUtils.compareElementsCount(beforeHover, afterHover);
		
		Assert.assertTrue(rVal, "The review stars are not litting up on hover.");
	}

	public void writeAReview() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(fourStar));
		SeleniumUtils.hoverAndClickOnElement(driver, fourStar);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(submitButton));

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(selectDropdown));
		SeleniumUtils.click(selectDropdown);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(optionToSelect));
		SeleniumUtils.click(optionToSelect);

		String reviewText = "This is a Test Insurance Company review on wallethub app for Health Insurance from the test profile. This is used only for testing purpose.";
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(reviewTextarea));
		SeleniumUtils.sendKeys(reviewTextarea, reviewText);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		SeleniumUtils.click(submitButton);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(reviewSucessMsg));
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		SeleniumUtils.click(continueButton);

		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(loggedInUser));
		SeleniumUtils.hoverOnElement(driver, loggedInUser);

		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(userProfile));
		SeleniumUtils.click(userProfile);

		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(myReview));
		SeleniumUtils.click(myReview);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(recentReviewBy));
		String reviewBy = SeleniumUtils.getText(recentReviewBy);

		boolean r_Val = SeleniumUtils.compareStrings(reviewBy, " Your Review");
		Assert.assertTrue(r_Val,
				"The review feed is not updated as the user's review is not visible in the review feed.");

	}
}
