package com.qa.ScanAndSlideInfo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetSlideStatusTest extends BaseClass {
	
  public GetSlideStatusTest() throws IOException {
		super(); //Calling the baseclass constructor

	}
//This Method validates the slide status API 
@Test
  public void testSlideStatus() throws InterruptedException {
	
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/slide_status");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
