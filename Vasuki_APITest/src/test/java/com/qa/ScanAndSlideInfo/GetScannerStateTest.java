package com.qa.ScanAndSlideInfo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetScannerStateTest extends BaseClass{
  public GetScannerStateTest() throws IOException {
		super(); //Calling the baseclass constructor
		
	}
//This method validates the scanner state API status
@Test
  public void testScannerState() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/get_scanner_state");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
