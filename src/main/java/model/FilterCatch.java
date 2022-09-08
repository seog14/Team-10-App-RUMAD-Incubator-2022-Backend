package model;

import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class FilterCatch extends JsonModel {
	private String species;
	private String min_length;
	private String max_length;
	private String min_weight;
	private String max_weight;
	private String min_temp;
	private String max_temp;
	private String min_baro;
	private String max_baro;
	private String min_humid;
	private String max_humid;
	private String min_date;
	private String max_date;
	private String city;
	
	
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getMin_length() {
		return min_length;
	}
	public void setMin_length(String min_length) {
		this.min_length = min_length;
	}
	public String getMax_length() {
		return max_length;
	}
	public void setMax_length(String max_length) {
		this.max_length = max_length;
	}
	public String getMin_weight() {
		return min_weight;
	}
	public void setMin_weight(String min_weight) {
		this.min_weight = min_weight;
	}
	public String getMax_weight() {
		return max_weight;
	}
	public void setMax_weight(String max_weight) {
		this.max_weight = max_weight;
	}
	public String getMin_temp() {
		return min_temp;
	}
	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}
	public String getMax_temp() {
		return max_temp;
	}
	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}
	public String getMin_baro() {
		return min_baro;
	}
	public void setMin_baro(String min_baro) {
		this.min_baro = min_baro;
	}
	public String getMax_baro() {
		return max_baro;
	}
	public void setMax_baro(String max_baro) {
		this.max_baro = max_baro;
	}
	public String getMin_humid() {
		return min_humid;
	}
	public void setMin_humid(String min_humid) {
		this.min_humid = min_humid;
	}
	public String getMax_humid() {
		return max_humid;
	}
	public void setMax_humid(String max_humid) {
		this.max_humid = max_humid;
	}
	public String getMin_date() {
		return min_date;
	}
	public void setMin_date(String min_date) {
		this.min_date = min_date;
	}
	public String getMax_date() {
		return max_date;
	}
	public void setMax_date(String max_date) {
		this.max_date = max_date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String projectionExpression() {
		String pExpress = "userId, lat, lon, tempr, humid, baro, city, species, length1,"
				+ " weight, date1"; 
		/*
		if(!(this.min_temp == (null))) {
			pExpress += "tempr, "; 
		}
		if(!(this.min_humid == (null))) {
			pExpress += "humid, "; 
		}
		if(!(this.min_baro == (null))) {
			pExpress += "baro, "; 
		}
		if(!(this.city == (null))) {
			pExpress += "city, "; 
		}
		if(!(this.species == (null))) {
			pExpress += "species, "; 
		}
		if(!(this.min_length == (null))) {
			pExpress += "length1, "; 
		}
		if(!(this.min_weight == (null))) {
			pExpress += "weight, "; 
		}
		if(!(this.min_date==(null))) {
			pExpress += "date1, "; 
		}
		
		if(pExpress.equals("userId, lat, lon, ")) {
			return null; 
		}
		else {
			pExpress = pExpress.substring(0, pExpress.length()-2);
			return pExpress; 
		}
		*/
		return pExpress; 
	}
	
	public String filterExpression() {
		String fExpress = "";
		if(!(this.min_temp == (null))) {
			fExpress += "(tempr between :min_temp and :max_temp) and "; 
		}
		if(!(this.min_humid == (null))) {
			fExpress += "(humid between :min_humid and :max_humid) and "; 
		}
		if(!(this.min_baro == (null))) {
			fExpress += "(baro between :min_baro and :max_baro) and "; 
		}
		if(!(this.city == (null))) {
			fExpress += "(city = :city) and "; 
		}
		if(!(this.species == (null))) {
			fExpress += "(species = :species) and "; 
		}
		if(!(this.min_length == (null))) {
			fExpress += "(length1 between :min_length and :max_length) and "; 
		}
		if(!(this.min_weight == (null))) {
			fExpress += "(weight between :min_weight and :max_weight) and "; 
		}
		if(!(this.min_date==(null))) {
			fExpress += "(date1 between :min_date and :max_date) and "; 
		}
		
		if(fExpress.equals("")) {
			return null; 
		}
		else {
			fExpress = fExpress.substring(0, (fExpress.length()-5));
			
			return fExpress; 
		}	
	}
	public boolean isEmpty() {
		if(min_temp == (null) 
				&& min_humid == (null) 
				&& min_baro == (null)
				&& city == (null)
				&& species == (null)
				&& min_length == (null)
				&& min_weight == (null)
				&& min_date == (null)) {
			return true; 
		}
		else 
			return false; 
	}
	public double toDouble(String a) {
		return Double.parseDouble(a);
	}
	public ValueMap filterMap() {
		ValueMap vm = new ValueMap(); 
		if(!(this.min_temp == (null))) {
			vm.withNumber(":min_temp", toDouble(this.min_temp)).withNumber(":max_temp", toDouble(this.max_temp));
		}
		if(!(this.min_humid==(null))) {
			vm.withNumber(":min_humid", toDouble(this.min_humid)).withNumber(":max_humid", toDouble(this.max_humid));
		}
		if(!(this.min_baro == (null))) {
			vm.withNumber(":min_baro", toDouble(this.min_baro)).withNumber(":max_baro", toDouble(this.max_baro));
		}
		if(!(this.city == (null))) {
			vm.withString(":city", this.city); 
		}
		if(!(this.species == (null))) {
			vm.withString(":species", this.species);  
		}
		if(!(this.min_length == (null))) {
			vm.withNumber(":min_length", toDouble(this.min_length)).withNumber(":max_length", toDouble(this.max_length));
		}
		if(!(this.min_weight == (null))) {
			vm.withNumber(":min_weight", toDouble(this.min_weight)).withNumber(":max_weight", toDouble(this.max_weight)); 
		}
		if(!(this.min_date==(null))) {
			vm.withString(":min_date", this.min_date).withString(":max_date", this.max_date);
		}
		
		return vm; 
	}
	

}
