package com.qa.miscellenious;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContactUsTest extends BaseClass {
  public ContactUsTest() throws IOException {
		super();  //Calling the baseclass constructor

	}

//This method validates the Contact us API
  @SuppressWarnings("unchecked")
@Test
  public void testContactUs() {
	RestAssured.baseURI = baseURL;
	RequestSpecification request = RestAssured.given();
	
	
	request.header("Content-Type", "application/json");
	

	JSONObject jobj = new JSONObject();
	
	jobj.put("email_id",prop.getProperty("emailId"));  //These are the comment info
	jobj.put("mobile_number", prop.getProperty("mobileNo"));
	jobj.put("title", prop.getProperty("title"));
	
	request.body(jobj.toJSONString());

	Response resp = request.post("/contact_us");

	
	int statusCode = resp.getStatusCode();
	Assert.assertEquals(statusCode,200, "Status Code is not 200");
  }
}
