package assignments;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.base.TestBase;
import com.facebook.pageObjects.LoginFb;
import com.facebook.pageObjects.HomePage;


public class Assignment1 extends TestBase {
	WebDriver driver;
	Properties config;
	
	@BeforeTest
	public void beforeTest() {
		driver=initialization();
		config=configSetup();		
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".jpg"));
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
    }
	
	@AfterTest
	public void afterTest() {
		driver.quit();;
	}
	@Test(priority=0)
	public void navigateTo() {
		
		String url=config.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
	}
	@Test(priority=1)
	public void verifyValidLogin() throws InterruptedException {
		
		String user=config.getProperty("user");
		String password=config.getProperty("password");
		
		LoginFb login=new LoginFb(driver);
		login.setUserName(user);
		login.setPassword(password);
		login.clickOnLoginButton();
		Thread.sleep(1000);
		login.clickOnNotNowButton();
	}
	
	@Test(priority=2, dependsOnMethods="verifyValidLogin")
	public void postMsg() throws InterruptedException {
		
		String text=config.getProperty("textToPost");
		
		HomePage home=new HomePage(driver);				
		home.setPostText(text);
		home.clickPost();
	}
	
	
}
