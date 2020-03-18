package com.qa.OQTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RunOQGetTest extends BaseClass {
  public RunOQGetTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
//this method validates the status of the QC run
@Test
  public void testRunOQ() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/oq");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
