package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Catch; 

import repository.CatchRepository;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class PutCatch implements RequestStreamHandler {

	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		
	    JSONParser parser = new JSONParser();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	    JSONObject responseJson = new JSONObject();
	    try {
	    	JSONObject responseBody = new JSONObject();
	        JSONObject event = (JSONObject) parser.parse(reader);
	        context.getLogger().log("PutCatch " + event);
	        if (event.get("body") != null) {
	        	Catch caught = (Catch) Catch.newInstance(Catch.class, (String) event.get("body"));
	        	CatchRepository catchRep = new CatchRepository(); 

	        	catchRep.save(caught); 
	        	responseBody.put("message", "New catch created " + caught.getUserId());
	        	responseBody.put("error", "0"); 
	        	}
	        	
	       

	        JSONObject headerJson = new JSONObject();
	        headerJson.put("Content-Type", "application/json");

	        responseJson.put("statusCode", 200);
	        responseJson.put("headers", headerJson);
	        responseJson.put("body", responseBody.toString());
	        

	    } catch (ParseException pex) {
	    	
	        responseJson.put("statusCode", 400);
	        responseJson.put("exception", pex);
	        
	    }
	    

	    OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
	    writer.write(responseJson.toString());
	    writer.close();
	}

}
