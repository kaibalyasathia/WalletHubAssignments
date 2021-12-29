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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.wallethub.pageObjects.WalletHub_LoginPage;
import com.wallethub.pageObjects.WalletHub_ReviewPage;

public class Assignment2 extends TestBase {
	WebDriver driver;
	Properties config;
	
	@BeforeTest
	public void beforeTest() {
		driver=initialization();
		config=configSetup();		
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
		driver.quit();
	}
	@Test(priority=0)
	public void navigateToWallethub() {
		
		String url=config.getProperty("wallethubUrl");
		driver.get(url);
		driver.manage().window().maximize();
	}
	@Test(priority=1)
	public void loginWallethub()  {
		
		String user=config.getProperty("wallethubUser");
		String pwd=config.getProperty("wallethubPwd");
		
		WalletHub_LoginPage loginPage=new WalletHub_LoginPage(driver);		
		loginPage.clickLoginLink();
		loginPage.setUserName(user);
		loginPage.setPassword(pwd);
		loginPage.clickLoginButton();
		
	}
	@Test(priority=2, dependsOnMethods="loginWallethub")
	public void addReviewWallethub()  {
		
		WalletHub_ReviewPage reviewPage=new WalletHub_ReviewPage(driver);		
		reviewPage.clickReviewsLink();
		reviewPage.checkIfReviewed();
		reviewPage.hoverOnReviewStars();
		reviewPage.writeAReview();
		
	}

}
