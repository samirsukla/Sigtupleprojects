package com.qa.ScanAndSlideInfo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StopScanTest extends BaseClass {
  public StopScanTest() throws IOException {
		super(); //Calling the baseclass constructor
		
	}
//This method helps to stop the scan
@Test
  public void testStopScan() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.post("/stop_scan");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
