package com.qa.logInOutTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequireLoginTest extends BaseClass {
  public RequireLoginTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
//This method validates If the application requires login or not
@Test
  public void testRequireLogin() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/require_login");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
