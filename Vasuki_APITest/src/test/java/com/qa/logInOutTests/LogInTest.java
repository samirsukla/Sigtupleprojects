package com.qa.logInOutTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import com.qa.base.BaseClass;
import com.qa.dataValidation.LogInAPIValidation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LogInTest extends BaseClass {
	public LogInAPIValidation loginvalid; 
	static Map<String,String> map;

	public LogInTest() throws IOException {
		super(); // Calling the baseclass constructor

	}

//This method validates the login API status
	@SuppressWarnings("unchecked")
	@Test
	public void logInTest() throws JsonProcessingException, IOException {
		loginvalid = new LogInAPIValidation();
		map = new HashMap<String, String>();
		boolean flag=false;
		
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		JSONObject jobj = new JSONObject();

		jobj.put("name", prop.getProperty("login_username")); // Passing the login credentials
		jobj.put("password", prop.getProperty("login_password"));
		request.body(jobj.toJSONString());

		Response resp = request.post("/login");
		int statusCode = resp.getStatusCode();

		String respBody = resp.getBody().asString();
		
		String installation_id = JsonPath.read(respBody, "$.response.message.installation_id");

		
		int product_size = JsonPath.read(respBody, "$.response.message.products.length()");
		
		for (int i=0;i<product_size;i++) {
			String product_id = JsonPath.read(respBody, "$.response.message.products["+i+"].product_id");
			String product_name = JsonPath.read(respBody, "$.response.message.products["+i+"].product_name");
			map.put(product_name, product_id );
		}
		
		boolean validation_status = loginvalid.validateLoginApi(installation_id,map);


		//List<String> product_Ids = JsonPath.read(respBody,
		//		"$.response.message.products[?(@.product_name == 'Shonit')].product_id"); 
		productId = map.get("Shonit"); 
		System.out.println(productId);
		
		if(statusCode==200) {
			if(validation_status==true) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		Assert.assertTrue(flag, "LogIn API Validation failed");

	}
}
