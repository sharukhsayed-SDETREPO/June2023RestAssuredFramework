package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.BaseTest.BaseTest;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class OAuth2AmadeusTest extends BaseTest{

	private static String accesstoken;
	
	@Parameters({"baseURI","grant_type","client_id","client_secret"})
	@BeforeMethod
	public void AccessToken(String baseURI,String granttype,String clientId,String ClientSecrt) {
		resclient=new  RestClient(prop, baseURI);
 accesstoken=	resclient.getAccesstoken(AMADEUS_ACCESS_ENDPOINT, granttype, clientId, ClientSecrt);
	}
	
	
	
	
	
	
	
	@Test
	public void GetFlightDetails() {
		
		resclient=new  RestClient(prop, baseURI);
		
		Map<String,String> hmap=new HashMap<String,String>();
		
		hmap.put("origin", "PAR");
		hmap.put("maxPrice", "200");
		
		Map<String,String> qhmap=new HashMap<String,String>();
		qhmap.put("Authorization","Bearer "+accesstoken);
		String sdt=resclient.get(AMADEUS_FLIGHT_ENDPOINT, qhmap, hmap, false, true)
		  .then().log().all()
		  .assertThat()
		  .statusCode(APIHttpStatus.OK_200.getCode())
		  .and()
		  .extract().response().jsonPath().getString("data[0].type");
		  System.out.println("type  ===>" +sdt);
	}
}
