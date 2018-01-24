package com.cfysu.webservice.jdk.client;

public class TestClient{
	public static void main(String[] args){
	BusinessService service = new BusinessService();
	Business business = service.getBusinessPort();
		System.out.println(business.echo("cj"));
	}
}