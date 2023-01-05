import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC01_Get_Request {
	
	@Test
	void getusers() {
		//Specify base uri
		
		RestAssured.baseURI="http://localhost:3000";
		
		//Request Object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response Object
		Response response= httprequest.request(Method.GET,"/users");
		
		//Print response in the console window
		String responsebody=response.getBody().asString();
		System.out.println("Response body is" +responsebody);
		
		//status code validation
		int statuscode= response.getStatusCode();
		System.out.println("Status code is" +statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//status line validation
		String statusLine=response.getStatusLine();
		System.out.println("Status Line is" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

}
