package com.employeeapi.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.TestListener;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.employeeapi.base.TestBase;

public class Listeners extends TestBase implements ITestListener 
{
public ExtentHtmlReporter htmlreporter;
public ExtentReports extent;
public ExtentTest test;


public void onStart(ITestContext Context)
{
	htmlreporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/myReport.html");//specify location
	htmlreporter.config().setDocumentTitle("Automation Report");//tile of report
	htmlreporter.config().setReportName("Functional Testing");//name of report
	htmlreporter.config().setTheme(Theme.DARK);
	
	extent=new ExtentReports();
	extent.attachReporter(htmlreporter);
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "austin");
	System.out.println("Execution started :"+Context.getName());
	
}

public void onTestStart(ITestResult result) {
    System.out.println("Test case :"+result.getName());
    test = extent.createTest(result.getName());
    
}
public void onTestSuccess(ITestResult result) {
	test=extent.createTest(result.getName());//create new entry
	test.log(Status.PASS,"Test Case Passed is "+result.getName());
	
}
public void onTestFailure(ITestResult result) {
	test=extent.createTest(result.getName());//create new entry
	test.log(Status.FAIL, "Test Case Failed is "+result.getName());
	test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());//to add error exception in extent report
}
public void onTestSkipped(ITestResult result) {
	test=extent.createTest(result.getName());//create new entry
	test.log(Status.SKIP, "Test Case Skippeded is "+result.getName());
}
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
}
public void onFinish(ITestContext context) {
    System.out.println("Execution finished :"+context.getName());
    extent.flush();
}


public void onTestStarted(Test test) {
	// TODO Auto-generated method stub
	
}


public void onTestRemoved(Test test) {
	// TODO Auto-generated method stub
	
}


public void onNodeStarted(Test node) {
	// TODO Auto-generated method stub
	
}


public void onLogAdded(Test test, Log log) {
	// TODO Auto-generated method stub
	
}


public void onCategoryAssigned(Test test, Category category) {
	// TODO Auto-generated method stub
	
}

public void onAuthorAssigned(Test test, Author author) {
	// TODO Auto-generated method stub
	
}

public void onDeviceAssigned(Test test, Device device) {
	// TODO Auto-generated method stub
	
}

public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
	// TODO Auto-generated method stub
	
}

public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
	// TODO Auto-generated method stub
	
}

public void onScreencastAdded(Test test, Screencast screencast) throws IOException {
	// TODO Auto-generated method stub
	
}

}
