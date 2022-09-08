package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import model.Catch;
import model.FilterCatch;
import repository.CatchRepository;

public class ScanCatches implements RequestStreamHandler{
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		
	    JSONParser parser = new JSONParser();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	    JSONObject responseJson = new JSONObject();
	    try {
	    	JSONObject responseBody = new JSONObject();
	        JSONObject event = (JSONObject) parser.parse(reader);
	        context.getLogger().log("ScanCatches invoked " + event);
	        if (event.get("body") != null) {
	        	FilterCatch filter = (FilterCatch) FilterCatch.newInstance(FilterCatch.class, (String) event.get("body"));
	        	if(!filter.isEmpty()) {
		        	CatchRepository catchRep = new CatchRepository();
		        	ArrayList<Catch> catches = new ArrayList<Catch>();
		        	catches = catchRep.filteredCatches(filter); 
		        	Gson gson = new Gson(); 
		        	responseBody.put("message", "Results found"); 
	        		responseBody.put("error", "0");
	        		responseBody.put("catches", gson.toJson(catches));
		        	
		        	if(catches.isEmpty()) {
		        		responseBody.put("message", "No results found"); 
		        		responseBody.put("error", "1");
		        		responseBody.put("catches", null); 
		        	}
	        	}
	        	else {
	        		responseBody.put("message", "Invalid request"); 
	        		responseBody.put("error", "2"); 
	        		responseBody.put("catches", null); 
	        	}
	        	
	        	
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
