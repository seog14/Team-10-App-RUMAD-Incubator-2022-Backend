package repository;

import model.User;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;

public class UserRepository {
	private DynamoDB dynamoDb; 
	private String DYNAMODB_TABLE_NAME = "User";
	private Regions REGION = Regions.US_EAST_1;
	
	private void initDynamoDbClient() {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
	
	public UserRepository() {
		initDynamoDbClient();
	}
	
	public PutItemOutcome save(User user) {
		
		Item item = new Item();
		item.withPrimaryKey("userId", user.getUserId());
		item.withString("password", user.getPassword());
		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).putItem(new PutItemSpec().withItem(item));
	
	}
	
	public boolean exist(String user) {
		Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME); 
		Item item = table.getItem("userId", user); 
		if(item != null) {
			return true; 
		}
		else
			return false; 
	}
	
	public int logIn(String user, String password) {
		Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME); 
		if (user == null)
		{
			return 1;
		}
		Item item = table.getItem("userId", user); 
		if(item != null && item.getString("password").equals(password)) {
			return 0; 
		}
		else if(item == null) {
			return 1; 
		}
		else {
			return 2; 
		}
	}
		
}
