package utils;



import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;



public class SeleniumUtils {
	
	//public static WebDriver driver;
	
	public static void sendKeys(WebElement e,String str ) {		
		try {			
			e.sendKeys(str);			
		}catch(NoSuchElementException ex) {
			Assert.fail("Element Not Found"+ e+" |Error - "+ex);
		}		
	}
	
	public static void click(WebElement e ) {
		try {
			e.click();
		}catch(NoSuchElementException ex) {
			Assert.fail("Element Not Found"+ e+" |Error - "+ex);
		}		
	}
	
	public static boolean isDisplayed(WebElement e ) {
		try {
			 return e.isDisplayed();
		}catch(NoSuchElementException ex) {
			
			System.out.println("Element Not Found"+ e+" |Error - "+ex);
			return false;
			
		}		
	}
	
	public static void hoverOnElement(WebDriver driver,WebElement e ) {
		Actions a=new Actions(driver);
		try {
			Thread.sleep(1000);
			  a.moveToElement(e).build().perform();
		}catch(NoSuchElementException ex) {			
			System.out.println("Element Not Found"+ e+" |Error - "+ex);		
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
	}
	public static void hoverAndClickOnElement(WebDriver driver,WebElement e ) {
		Actions a=new Actions(driver);
		try {
			Thread.sleep(1000);
			  a.moveToElement(e).click().build().perform();
		}catch(NoSuchElementException ex) {			
			System.out.println("Element Not Found"+ e+" |Error - "+ex);		
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
	}
	public static String getText(WebElement e) {
		try {
			System.out.println(e.getText());
			return e.getText();
		}catch(NoSuchElementException ex) {			
			System.out.println("Element Not Found"+ e+" |Error - "+ex);	
			return null;
		}
		
	}
	public static String getCSSValue(WebElement e,String arg) {
		try {
			return e.getCssValue(arg);
		}catch(NoSuchElementException ex) {			
			System.out.println("Element Not Found"+ e+" |Error - "+ex);	
			return null;
		}
		
	}
	public static boolean compareStrings(String str1, String str2){
		if(str1!=null||str2!=null) {
			if( str1.trim().equalsIgnoreCase(str2.trim())) {
				System.out.println( "String1: - "+ str1+" |String2: - "+str2);
				return true;
			}else {
				System.out.println( "Fail: String1: - "+ str1+" |String2: - "+str2);
				return false;
			}
		}else {
			System.out.println("One of the compare string is null. String1: - "+ str1+" |String2: - "+str2);
			return false;
		}
		
	}
	public static boolean compareElementsCount(List<WebElement> l1, List<WebElement> l2){
		int s1=l1.size();
		int s2=l2.size();
		if(s1!=0||s2!=0) {
			if( s2>s1) {
				System.out.println("The size of list2 is greater than list1.");
				return true;
			}
			else {
				System.out.println("Fail: The size of list2 is not greater than list1.");
				return false;
				//Assert.fail("The review stars are not getting lit up on hover.");
			}
		}else {
			Assert.fail("One of the list of elements is empty.");
			return false;
			
		}
		
	}
	public static boolean compareElementsSize(int s1, int s2){
		
		if(s1!=0||s2!=0) {
			if( s2>s1) {
				System.out.println("The element size2 is greater than element size1");
				return true;
			}
			else {
				System.out.println("Fail: The element size2 is not greater than element size1");
				return false;
			}
		}else {
			Assert.fail("One of the element sizes is empty.");
			return false;
		}
		
	}
}
