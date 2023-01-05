package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("*********Started TC004_Put_Employee_Record********");
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		//Jsonobject is a class that represents simple Json. We can add key-value pairs using the put method
		//{"name": "John123x", "salary": "123", "age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName); //cast
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		//add a header stating the request body is json
				httpRequest.header("Content-type", "application/json");
				
				//Add the json to the body of the request
				httpRequest.body(requestParams.toJSONString());
				
				response=httpRequest.request(Method.PUT, "/update/"+empID);
				Thread.sleep(5000);
				}
	
	@Test
	void checkResponseBody() {
		String ResponseBody= response.getBody().asString();
		Assert.assertEquals(ResponseBody.contains(empName), true);
		Assert.assertEquals(ResponseBody.contains(empName), true);
		Assert.assertEquals(ResponseBody.contains(empName), true);
		
	}
	
	@Test
	void checkStatusCode() {
		int statusCode=response.getStatusCode();//getting status code
		//Assert.assertEquals(statusCode, 200);
		softAssert(statusCode, 200);
	}
	@Test
	void checkStatusLine() {
		String statusLine=response.getStatusLine();//getting status Line
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		softAssert(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType() {
		String contentType=response.header("Content-Type");
		//Assert.assertEquals(contentType, "application/json");
		softAssert(contentType, "application/json");
	}
	@Test
	void checkServerType() {
		String serverType=response.header("Server");
		//Assert.assertEquals(serverType, "Apache");
		softAssert(serverType, "Apache");
	}
	@Test
	
	void checkContentEncoding() {
		String contentEncoding=response.header("Content-Encoding");
		//Assert.assertEquals(contentEncoding, "gzip");
		softAssert(contentEncoding, "gzip");
	}
	@AfterClass
	void TearDown() {
		logger.info("*****Finished TC004_Put_Employee_Recordd*******");
	}
	}


