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

import model.User; 

import repository.UserRepository;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;


public class Login implements RequestStreamHandler{
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
			
		    JSONParser parser = new JSONParser();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    JSONObject responseJson = new JSONObject();
		    try {
		    	JSONObject responseBody = new JSONObject();
		        JSONObject event = (JSONObject) parser.parse(reader);
		        context.getLogger().log("Login invoked " + event);
		        if (event.get("body") != null) {
		        	User user = (User) User.newInstance(User.class, (String) event.get("body"));
		        	UserRepository userRep = new UserRepository(); 
		        	int error = userRep.logIn(user.getUserId(), user.getPassword()); 
		        	if(error == 0) {
		        		responseBody.put("message", "User: " + user.getUserId() + " successfully logged in"); 
		        		responseBody.put("error", "0"); 
		        		responseBody.put("userId", user.getUserId()); 
		        	}
		        	else if (error == 1){
		        		responseBody.put("message", "Username does not exist");
		        		responseBody.put("error", "1"); 
		        		responseBody.put("userId", null); 
		        	}
		        	else {
		        		responseBody.put("message", "Password is incorrect");
		        		responseBody.put("error", "2"); 
		        		responseBody.put("userId", null); 
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
