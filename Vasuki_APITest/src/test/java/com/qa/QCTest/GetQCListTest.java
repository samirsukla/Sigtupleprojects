package com.qa.QCTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetQCListTest extends BaseClass {
  public GetQCListTest() throws IOException {
		super(); //Calling the baseclass constructor

	}
//This Method validates the QCList API status
@Test
  public void testQCList() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/get_qc_list");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
