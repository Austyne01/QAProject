package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	
	void deleteEmployee() throws InterruptedException{
		logger.info("*********Started TC005_Delete_Employee_Record********");
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		
		response= httpRequest.request(Method.GET, "/employees");
		//First get the Json path object instance from the Response interface
		JsonPath jsonPathEvaluator= response.jsonPath();
		//CaptureID
		String empID=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/"+empID);//Pass ID to delete record
		Thread.sleep(3000);
		
	}
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		//Assert.assertEquals(responseBody.contains("Successfully deleted records"), true);
		softAssert(responseBody.contains("Successfully deleted records"));
		
	}
	@Test
	void checkStatusCode()
	{
		int statusCode=response.getStatusCode();
		//Assert.assertEquals(statusCode, 200);
		softAssert(statusCode, 200);
		
	}
	@Test
	
	void checkStatusLine()
	{
		String statusLine=response.getStatusLine();
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		softAssert(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	
	void checkContentType()
	{
		String contentEncoding= response.header("Content-Encoding");
		//Assert.assertEquals(contentEncoding, "gzip");
		softAssert(contentEncoding, "gzip");
	}
	@Test
	
	void checkServerType()
	{
		String serverType= response.header("Server");
		//Assert.assertEquals(serverType, "nginx/1.14.1");
		softAssert(serverType, "nginx/1.14.1");
	}
	@Test
	
	void checkContentEncoding()
	{
		String contentEncoding=response.header("Content-Encoding");
		//Assert.assertEquals(contentEncoding, "gzip");
		softAssert(contentEncoding, "gzip");
	}
	@AfterClass
	void TearDown() {
		logger.info("*****Finished TC005_Delete_Employee_Recordd*******");
	}

	}
	


