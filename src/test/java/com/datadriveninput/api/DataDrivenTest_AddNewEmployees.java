package com.datadriveninput.api;

import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DataDrivenTest_AddNewEmployees {
	
@Test(dataProvider="empdataprovider")
	void postNewEmployees(String ename, String esal, String eage) throws InterruptedException{
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	RequestSpecification httpRequest=RestAssured.given();
	//here we create data which we can send with Post request
	JSONObject requestParams= new JSONObject();
	requestParams.put("name", ename);
	requestParams.put("salary", esal);
	requestParams.put("age", eage);
	//Add a header stating the request body is json
	httpRequest.header("Content-Type","application-json");
	
	httpRequest.header("Accept-Encoding","gzip");
	//add json to the body of request
	httpRequest.body(requestParams.toJSONString());
	//httpRequest.body(requestParams.)
	//post request
	Response response=httpRequest.request(Method.POST,"/create");
	
	//capture response body to perform validations
	 String responsebody=response.getBody().asString();
	 System.out.println("Response body is "+responsebody);
	 
	 Assert.assertEquals(responsebody.contains(ename), true);
	 Assert.assertEquals(responsebody.contains(esal), true);
	 Assert.assertEquals(responsebody.contains(eage), true);
	 Thread.sleep(50000);
	 
	int statuscode= response.getStatusCode();
	Assert.assertEquals(statuscode, 200);	
}

@DataProvider(name="empdataprovider")
String[][] getEmpData() throws IOException 
{
	//Read data from excel
	String path=System.getProperty("user.dir")+"/src/test/java/com/datadriveninput/api/EmpData.xlsx";
	int rownum=XLUtils.getRowCount(path, "Sheet1");
	int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
	//String empdata[][]= {{"abc123", "20000","20"},{"pqr234","30000","22"},{"def789","30000","24"}};
	String empdata[][]=new String[rownum][colcount];
	for(int i=1; i<=rownum;i++) {
		for(int j=0;j<colcount;j++) {
			empdata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
		}
		
	}
	return(empdata);
}

}
