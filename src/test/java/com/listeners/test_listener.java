package com.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pojo.base_class;
import com.utilities.screenshot_utility;

public class test_listener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testInstance = result.getInstance();

		if (testInstance instanceof base_class) {

			WebDriver driver = ((base_class) testInstance).driver; // or use getDriver()

			String screenshotPath = screenshot_utility.captureScreenshot(driver, result.getMethod().getMethodName());

			System.out.println("Screenshot saved at: " + screenshotPath);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		ITestListener.super.onFinish(context);
	}

}
