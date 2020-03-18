package com.qa.miscellenious;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class AboutUsTest extends BaseClass {
  public AboutUsTest() throws IOException {
		super(); //Calling the baseclass constructor

	}
//This method will validate the about us API
@Test
  public void testAboutUs() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	Response resp = request.get("/about_us");
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
  }
}
