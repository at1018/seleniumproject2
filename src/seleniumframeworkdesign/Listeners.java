package seleniumframeworkdesign;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReport extentReport=new ExtentReport();
	ExtentReports extent=extentReport.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	ExtentTest test;
	WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
//	System.out.println("Method started"+ result.getName());
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	@Override
	public void onStart(ITestContext contextStart) {
//	System.out.println("onStart method started");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//	System.out.println("Method failed with certain success percentage"+ result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
//	System.out.println("Test case got failed");
//		test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());
		try {
//			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//			driver=(WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);
			driver=(WebDriver) result.getTestContext().getAttribute("driver");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
//	System.out.println("Method skipped"+ result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
//	System.out.println("Test case executed successfully");

	}
	@Override
	public void onFinish(ITestContext contextFinish) {
//	System.out.println("onFinish method finished");
		extent.flush();

	}


}
