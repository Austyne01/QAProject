package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="54768";
	
    public ExtentReports extent = new ExtentReports();
    public ExtentSparkReporter spark;
    public ExtentTest test;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger=Logger.getLogger("EmployeesRestAPI");//added Logger
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	public void softAssert(String expected, String actual) {
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertEquals(expected, actual);
	}
	
	public void softAssert(boolean condition) {
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertTrue(condition);
	}
	public void softAssert(int expected, int statuscode) {
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertEquals(expected, statuscode);
	}
	

}
