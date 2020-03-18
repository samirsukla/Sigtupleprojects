package com.qa.ScanAndSlideInfo;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductSelectionTest extends BaseClass {
	
  public ProductSelectionTest() throws IOException {
		super(); //Calling the baseclass constructor
		
	}
//This method selects a particular product
@SuppressWarnings("unchecked")
@Test
  public void testProductSelection() {
	  
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		
		
		request.header("Content-Type", "application/json");
		

		JSONObject jobj = new JSONObject();
		
		jobj.put("product_id","56c815cc56685df2014df1fb");  //Fetching the product ID from the Login API response
		
		request.body(jobj.toJSONString());

		Response resp = request.post("/product_selection");
		
		
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode,200, "Status Code is not 200");
  }
}
