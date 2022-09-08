package model;

import java.util.Date;

import com.google.gson.Gson;

public class Catch extends JsonModel{
	private String userId; 
	private double tempr; 
	private double humid; 
	private double baro; 
	private double lat; 
	private double lon; 
	private String city; 
	private String species; 
	private double length1; 
	private double weight;
	private String date1; 
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public double getTemp() {
		return tempr;
	}
	public void setTemp(double tempr) {
		this.tempr = tempr;
	}
	
	public double getHumid() {
		return humid;
	}
	public void setHumid(double humid) {
		this.humid = humid;
	}
	
	public double getBaro() {
		return baro;
	}
	public void setBaro(double baro) {
		this.baro = baro;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public double getLength() {
		return length1;
	}
	public void setLength(double length) {
		this.length1 = length;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getDate() {
		return date1;
	}
	public void setDate(String date) {
		this.date1 = date;
	} 
	
}
