package com.cfysu.model;

import java.util.HashMap;
import java.util.Map;


public class Car {

	public int wheelNum;

	private String brand ="BMW";
	
	private Map<String, String> propertyMap = new HashMap<String, String>();
	
	private Integer Price = 1;
	
	public Car(int wheelNum, String brand){
		this.wheelNum = wheelNum;
		this.brand = brand;
	}
	
	public Car(){
		System.out.println("Car");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Map<String, String> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}
}

class Person{
}