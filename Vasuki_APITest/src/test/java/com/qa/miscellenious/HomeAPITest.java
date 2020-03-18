package com.qa.miscellenious;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HomeAPITest extends BaseClass {
  public HomeAPITest() throws IOException {
		super(); //Calling the baseclass constructor
		
	}
//This method validates the home API and If successfully triggered, redirects to the home page.
@Test
  public void testHomeAPI() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.post("/home");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
