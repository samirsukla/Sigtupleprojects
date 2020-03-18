package com.qa.dataValidation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.utility.ConnectToMongoDB;

public class LogInAPIValidation {
	public ConnectToMongoDB mongoConn;
	static Map<String,String> productIds_fromDB;
  
  public boolean validateLoginApi(String installation_id, Map<String,String> productIds_fromAPI) throws JsonProcessingException, IOException {
	  mongoConn = new ConnectToMongoDB();
	  productIds_fromDB = new HashMap<String,String>();
	  boolean flag = false;
	  productIds_fromDB =  mongoConn.getProductNamesusingProductId(installation_id);
	   if(productIds_fromAPI.size()==productIds_fromDB.size()) {
		   if(productIds_fromAPI.equals(productIds_fromDB)) {
			   flag = true;
		   }
		   else {
			   flag = false;
		   }
	   }
	  
	  return flag;
	  
  }
}
