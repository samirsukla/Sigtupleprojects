package com.qa.QCTest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DailyQCTest extends BaseClass {
  public DailyQCTest() throws IOException {
		super(); //Calling the baseclass constructor
	}
  
//This method validates the daily QC status
@SuppressWarnings("unchecked")
@Test
  public void testDailyQC() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
	

	JSONObject jobj = new JSONObject();
	
	jobj.put("slide_name","TEST_EDIT_SHONIT");
	
	request.body(jobj.toJSONString());

	Response resp = request.post("/daily_qc");
	
	
	int statusCode = resp.getStatusCode();

	Assert.assertEquals( statusCode,200,"Status Code is not 200");
	
  }
}
