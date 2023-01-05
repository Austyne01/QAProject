package com.employeeapi.testcases;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase{
	@BeforeClass
	void getAllEmployees() throws InterruptedException, FileNotFoundException
	{
		 PrintStream logfile = new PrintStream(new FileOutputStream("logs.txt"));//to add log file
		logger.info("********Started TC_001_Get_All_Employees********");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given().log().all().filter(RequestLoggingFilter.logRequestTo(logfile))
				.filter(ResponseLoggingFilter.logResponseTo(logfile)).contentType(ContentType.JSON);
		
		
		response=httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3000);
	}
	@Test
	void checkResponseBody() {
		logger.info("*********Checking Response Body*********");
		String responseBody=response.getBody().asString();
		logger.info("Response Body==>" +responseBody);
		//Assert.assertTrue(responseBody!=null);
		softAssert(responseBody!=null);
	}
	@Test
	void checkStatusCode() {
		logger.info("*****Checking Status Code********");
		int statusCode=response.getStatusCode(); //getting status code
		logger.info("Status Code is ==>" +statusCode);//200
		//Assert.assertEquals(statusCode, 200);
		softAssert(statusCode, 200);
	}
	@Test
	void checkResponseTime() {
		logger.info("*********Checking Response Time*********");
		long responseTime=response.getTime();
		logger.info("Response Time==>" +responseTime);
		if(responseTime>2000) {
			logger.warn("Response Time is greater than 2000");
			//Assert.assertTrue(responseTime>2000);
			softAssert(responseTime>2000);
		}
	}
		
		@Test
		void checkStatusLine() {
			logger.info("*********Checking Status Line*********");
			String statusLine=response.getStatusLine();
			logger.info("Status Line is==>" +statusLine);
			//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");		
			softAssert(statusLine, "HTTP/1.1 200 OK");
		}
		
		@Test
		void checkContentType() {
			logger.info("*********Checking Content Type*********");
			String contentType=response.header("contentType");
			logger.info("Content type is==>" +contentType);
			//Assert.assertEquals(contentType, "text/html; charset=utf-8");
			softAssert(contentType, "text/html; charset=utf-8");
			
				
	}
		@Test
		void checkServerType() {
			logger.info("*********Checking Server Type*********");
			String serverType=response.header("Server");
			logger.info("Server type is==>" +serverType);
			//Assert.assertEquals(serverType, "nginx/1.14.1");
			softAssert(serverType, "nginx/1.14.1");
			
			
		}
		
		@Test
		void checkContentCoding() {
			logger.info("*********Checking Content Coding*********");
			String contentEncoding=response.header("Content-Encoding");
			logger.info("Content encoding is==>" +contentEncoding);
			//Assert.assertEquals(contentEncoding, "gzip");
			softAssert(contentEncoding, "gzip");
			
		}
		
		@Test
		void checkContentLength() {
			logger.info("*********Checking Content Length*********");
			String contentLength=response.header("Content-Length");
			logger.info("Content Length==>" +contentLength);
			if(Integer.parseInt(contentLength)<100) {
				logger.warn("Content length is less than 100");
				//Assert.assertTrue(Integer.parseInt(contentLength)>100);
				softAssert(Integer.parseInt(contentLength)>100);
			
		}
		}
		@Test
		void checkCookies() {
			logger.info("*********Checking Cookies*********");
			String cookie=response.getCookie("PHPSESSID");
			
		}
		@AfterClass
			void tearDown() {
			logger.info("*********Finished TC001_Get_All_Employees********");
			
		}
	}
