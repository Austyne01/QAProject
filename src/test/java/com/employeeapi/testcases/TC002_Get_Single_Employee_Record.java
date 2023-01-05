package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*****Started TC002_Get_Single_Employee_Record*******");
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		response= httpRequest.request(Method.GET,"/employee/"+empID);
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		//Assert.assertEquals(responseBody.contains(empID), true);
		softAssert(responseBody.contains(empID));
	}
	
	@Test
	void checkStatuscode()
	{
		
		int statusCode=response.getStatusCode(); //getting status code
		
		//Assert.assertEquals(statusCode, 200);
		softAssert(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
	
		long responseTime=response.getTime();
		
		if(responseTime>2000) {
			logger.warn("Response Time is greater than 2000");
			//Assert.assertTrue(responseTime>2000);
			softAssert(responseTime>2000);
		}
		
	}
	@Test
	void checkStatusLine()
	{
		
		String statusLine=response.getStatusLine();
		
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
		softAssert(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
	
		String contentType=response.header("contentType");

		//Assert.assertEquals(contentType, "text/html; charset=utf-8");
		softAssert(contentType, "text/html; charset=utf-8");
			
}
	@Test
	void checkServerType() {
		
		String serverType=response.header("Server");
		logger.info("Server type is==>" +serverType);
		//Assert.assertEquals(serverType, "nginx/1.14.1");
		softAssert(serverType, "nginx/1.14.1");
		
	}

	
	@Test
	void checkContentLength() {
		
		String contentLength=response.header("Content-Length");
		logger.info("Content Length==>" +contentLength);
		if(Integer.parseInt(contentLength)<100) {
			logger.warn("Content length is less than 100");
			//Assert.assertTrue(Integer.parseInt(contentLength)<1500);
			softAssert(Integer.parseInt(contentLength)<1500);
		
	}
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********* Finished TC002_Get_Single_Employee_Record *************");
	}

}
