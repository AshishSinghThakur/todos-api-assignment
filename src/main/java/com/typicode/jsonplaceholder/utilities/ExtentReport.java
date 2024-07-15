package com.typicode.jsonplaceholder.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReport implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public String suiteName = "";

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports.html");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("Test Report");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		suiteName = context.getSuite().getName();
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
	}

	public static ExtentTest getTest() {
		return test.get();
	}

}