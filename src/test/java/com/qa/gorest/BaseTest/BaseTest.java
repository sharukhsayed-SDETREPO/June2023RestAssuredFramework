package com.qa.gorest.BaseTest;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.Config.ConfigurationManager;
import com.qa.gorest.RestClient.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	public static final String GOREST_ENDPOINT="/public/v2/users";
	public static final String CIRCUIT_ENDPOINT="/api/f1/";
	public static final String  REQ_ENDPOINT="/api/users";
	public static final String  AMADEUS_ACCESS_ENDPOINT="v1/security/oauth2/token";

	public static final String  AMADEUS_FLIGHT_ENDPOINT="v1/shopping/flight-destinations";
	
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient resclient;
	protected String baseURI;
	
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUP(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		config=new ConfigurationManager();
		prop=config.init_Prop();
		this.baseURI =baseURI;
	//	resclient=new RestClient(prop, baseURI);
	}
	
	
	
	
	
	
	
	
}
