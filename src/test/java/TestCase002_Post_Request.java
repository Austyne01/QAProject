import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TestCase002_Post_Request {

	
		
		@Test
		void createuser() {
			//Specify base uri
			
			RestAssured.baseURI="http://localhost:3000";
			
			//Request Object
			RequestSpecification httprequest=RestAssured.given();
			
			//Request payload/body along with Post
			
			JSONObject requestparams=new JSONObject();
			requestparams.put("id", 12);
			requestparams.put("first_name", "Zlatan");
			requestparams.put("last_name", "Ibrahimovich");
			requestparams.put("email", "zlatan@acm.in");
			
			httprequest.header("content-Type", "application/json");
			httprequest.body(requestparams.toJSONString());
			
			//Response Object
			Response response= httprequest.request(Method.POST,"/users");
			
			
			//Print response in the console window
			String responsebody=response.getBody().asString();
			System.out.println("Response body is" +responsebody);
			
			//status code validation
			int statuscode= response.getStatusCode();
			System.out.println("Status code is" +statuscode);
			Assert.assertEquals(statuscode, 201);
			
			//status line validation
			String statusLine=response.getStatusLine();
			System.out.println("Status Line is" +statusLine);
			Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
			
			String successcode=response.jsonPath().get("SuccessCode");
			Assert.assertEquals(successcode, "201");
		}

			
		
		

	}


