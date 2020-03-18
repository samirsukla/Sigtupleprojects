package com.qa.OQTest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RunOQPostTest extends BaseClass {
	  public RunOQPostTest() throws IOException {
			super(); //Calling the baseclass constructor
		}
//This method validates the status of the QC run
	@SuppressWarnings("unchecked")
	@Test
	  public void testRunOQPost() {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		

		JSONObject jobj = new JSONObject();
		
		jobj.put("type","run_oq");
		
		request.body(jobj.toJSONString());

		Response resp = request.post("/oq");
		
		
		int statusCode = resp.getStatusCode();

		Assert.assertEquals( statusCode,200,"Status Code is not 200");
	  }
	}