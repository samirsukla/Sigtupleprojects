package com.qa.logInOutTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LogoutTest extends BaseClass {
	
  public LogoutTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
//This method helps to logout of Vasuki
@Test
  public void testLogout() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.post("/logout");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
