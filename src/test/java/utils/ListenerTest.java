package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.TestBase;

public class ListenerTest extends TestBase implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		String testMethodNm=result.getMethod().getMethodName();
		failed(testMethodNm);
	}
	
}
