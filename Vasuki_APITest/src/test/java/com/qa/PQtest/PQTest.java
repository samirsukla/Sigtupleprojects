package com.qa.PQtest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PQTest extends BaseClass{
  public PQTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
//This method runs the PQ Test
@Test
  public void testPQ() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	
	
	String requestBody = "{\n" +    //Request payload for the PQ Test
			"\"slide_name\": \"Samir Sukla\",\n" + 
			"\"description\": \"MR Samir\",\n" + 
			"\"total_scans\": 5\n" + 
			"}";
	
	
	Response resp = request.
			when().
			header("Content-Type", "application/json").
			body(requestBody).post("/pq_test");
	
	

	int respCode = resp.getStatusCode();
	Assert.assertEquals(respCode, 200, "PQ Test is Failing.");
	
  }
}
