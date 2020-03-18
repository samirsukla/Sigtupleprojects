package com.qa.ScanAndSlideInfo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateSlideTest extends BaseClass {
  public CreateSlideTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
//This method validates the status of create slide API
@Test
  public void testCreateSlide() {
	  RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		
		
		String requestBody = "{\n" +                     //This is the request Payload for Shonit
				"    \"slide_name\": \"shonit\",\n" + 
				"    \"stain\": \"Auto\",\n" + 
				"    \"smear\": \"Lieshman\",\n" + 
				"    \"scan_input_data\": {\n" + 
				"        \n" + 
				"    }\n" + 
				"}\n" + 
				"";
		
		
		Response resp = request.
				when().
				header("Content-Type", "application/json").
				body(requestBody).post("/create_slide");
		
		

		String respBody = resp.getBody().asString();
		
		
		int respCode = resp.getStatusCode();
		Assert.assertEquals(respCode, 200, "Create Slide is Failing.");
  }
}
