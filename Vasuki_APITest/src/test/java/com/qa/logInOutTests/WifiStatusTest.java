package com.qa.logInOutTests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WifiStatusTest extends BaseClass {

	public WifiStatusTest() throws IOException {
		super(); // Calling the baseclass constructor

	}

	// This method checks the status of the wifi connectivity
  @Test
  public void testWifiStatus() {
	  	RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		Response resp = request.get("/wifi_status");
		
		int statusCode = resp.getStatusCode();

		Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
