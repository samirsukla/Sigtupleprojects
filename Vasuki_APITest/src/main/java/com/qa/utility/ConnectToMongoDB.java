package com.qa.utility;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.BSON;
import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class ConnectToMongoDB {
	static String password = "cpYIEDiGQU9ouqfRNJJMlBx4JJWg9G3nw8ixfzddTaXq8";
	static List<String> list_product;
	static Map<String,String> map;
	List<String> userDetails = new ArrayList<String>();

	// @Test
	public MongoDatabase mongoDBConnectionTest() throws JsonProcessingException, IOException {
		list_product = new ArrayList<String>();

		// Creating Credentials
		MongoCredential credential = MongoCredential.createCredential("samirks", "app-qa", password.toCharArray());

		// Creating a Mongo client
		MongoClient mongo = new MongoClient(new ServerAddress("mongo-qa.sigtuple.com", 33000),
				Collections.singletonList(credential));

		//System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongo.getDatabase("app-qa");
		return database;

	}

	public List<String> getProductListFromInstallations(String installation_id)
			throws JsonProcessingException, IOException {
		MongoDatabase database = mongoDBConnectionTest();
		MongoCollection<Document> collection = database.getCollection("installations");

		ObjectId id = new ObjectId(installation_id);
		BasicDBObject query = new BasicDBObject();
		query.append("_id", id);
		query.putAll((BSONObject) query);

		FindIterable<Document> iterDoc = collection.find(query);

		// Getting the iterator
		Iterator<Document> it = iterDoc.iterator();

		while (it.hasNext()) {
			Document doc = (Document) it.next();
			String s = doc.toJson();

			List<Object> product_list = (List<Object>) doc.get("products");
			int product_size = product_list.size();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode node;
			node = mapper.readTree(s);

			// Object obj = node.get("products");
			for (int i = 0; i < product_size; i++) {
				String product_id = node.get("products").get(i).get("product").get("$oid").toString().replaceAll("^\"|\"$", "");
				list_product.add(product_id);
			}


		}
		return list_product;

	}
	
	public Map<String,String> getProductNamesusingProductId(String installation_id) throws JsonProcessingException, IOException {
		map = new HashMap<String, String>();
		List<String> product_Ids  = getProductListFromInstallations(installation_id);
		
		MongoDatabase database = mongoDBConnectionTest();
		MongoCollection<Document> collection = database.getCollection("products");
		for(int i=0;i<product_Ids.size();i++) {
			String product_name = null;
			String product_Id = product_Ids.get(i);
			ObjectId id = new ObjectId(product_Id);
			BasicDBObject query = new BasicDBObject();
			query.append("_id", id);
			query.putAll((BSONObject) query);

			FindIterable<Document> iterDoc = collection.find(query);

			// Getting the iterator
			Iterator<Document> it = iterDoc.iterator();

			while (it.hasNext()) {
				Document doc = (Document) it.next();
				product_name = doc.getString("name");
			}
			map.put(product_name, product_Id);
		}
		
		//System.out.println(map);
		return map;
	}
	
	
//	=============================================================================================
	
	public String convertDateToString(String start_time) {
		//String start_time = "Thu Jan 16 12:11:04 IST 2020";
		final DateTimeFormatter inputFormat = 
		        DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
		final ZonedDateTime parsed = ZonedDateTime.parse(start_time, inputFormat);
		final DateTimeFormatter outputFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String finalTime = outputFormat1.format(parsed);
		System.out.println(finalTime);
		return finalTime;
		
	}
	
	@Test
	public void getAnalysisId()
			throws JsonProcessingException, IOException {
		MongoDatabase database = mongoDBConnectionTest();
		MongoCollection<Document> collection = database.getCollection("analysis_control");
		
		BasicDBObject andQuery = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	    obj.add(new BasicDBObject("sample_id", "AMIT"));
	    obj.add(new BasicDBObject("sample_desc", "testing"));
	    //obj.add(new BasicDBObject("start_ts", "16-01-2020"));
	    andQuery.put("$and", obj);
	  
	    System.out.println(andQuery.toString());
	    //List<String> start_time = new ArrayList<String>();
	    String analysis_id = null;
	    String patient_id =null;
	    ObjectId oid = new ObjectId();
	  
	    FindIterable<Document> iterDoc = collection.find(andQuery);
	    Iterator<Document> it = iterDoc.iterator();
	    while (it.hasNext()) {
	        
	        Document doc = (Document) it.next();
	        String start_time = (doc.getDate("start_ts")).toString();
	        System.out.println("Start Time ="+start_time);
	        String finalTime = convertDateToString(start_time);
	        if(finalTime.equals("09-01-2019"))
	        {
	        	oid = doc.getObjectId("_id");
	        	patient_id = doc.get("case_info",Document.class).getString("patient_id");
	        	
	        }
	        
	    }
	    System.out.println(oid);
	    System.out.println(patient_id);


	}
	
	
	
	@Test(priority=1,enabled=false)
	   public  void getUserDetails() throws JsonProcessingException, IOException  {
			MongoDatabase database = mongoDBConnectionTest();
			MongoCollection<Document> collection = database.getCollection("users");
			
			BasicDBObject andQuery = new BasicDBObject();
		    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		    obj.add(new BasicDBObject("username", "samir"));
		    
		    andQuery.put("$and", obj);
		  
		    System.out.println(andQuery.toString());
//		    List<String> userDetails = new ArrayList<String>();
		    
		    ObjectId oid = new ObjectId();
		  
		    FindIterable<Document> iterDoc = collection.find(andQuery);
		    Iterator<Document> it = iterDoc.iterator();
		    while (it.hasNext()) {
		        
		        Document doc = (Document)it.next();
		        String uname = doc.getString("name");
		        String emailId = doc.getString("email");
		        oid = doc.getObjectId("partner");
		        String partnerId = oid.toString();
		        userDetails.add(uname);
		        userDetails.add(emailId);
		        userDetails.add(partnerId);
		        
		    }
		    System.out.println(userDetails);
		   // return userDetails;


		}
	   @Test(priority=2,enabled=false)
	   public  void getPartnerDetails() throws JsonProcessingException, IOException  {
			MongoDatabase database = mongoDBConnectionTest();
			MongoCollection<Document> collection = database.getCollection("partners");
			
			ObjectId id = new ObjectId(userDetails.get(2));
			BasicDBObject query = new BasicDBObject();
			query.append("_id", id);
			query.putAll((BSONObject) query);
			String partner_name = null;
			FindIterable<Document> iterDoc = collection.find(query);

			// Getting the iterator
			Iterator<Document> it = iterDoc.iterator();
		    while (it.hasNext()) {
		        
		        Document doc = (Document)it.next();
		        partner_name = doc.getString("name");
		        
		    }
		    System.out.println(partner_name);
		   // return userDetails;


		}

}
