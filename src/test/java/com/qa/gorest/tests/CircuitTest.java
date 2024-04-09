package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.BaseTest.BaseTest;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends  BaseTest {

	
	
	@BeforeMethod
	public void RestClientSetup() {
		resclient=new RestClient(prop, baseURI);
	}
	
	@Test
	public void GetAllUSers_Test() {
		Response circuitresponse=resclient.get(CIRCUIT_ENDPOINT+"2017/circuits.json", false,true);
		
		
		circuitresponse.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
		
		//int statuscode=circuitresponse.getStatusCode();
		//System.out.println(statuscode);
	//	Assert.assertEquals(statuscode, APIHttpStatus.OK_200.getCode());
		
		JsonPathValidator js=new JsonPathValidator();
		List<String> countrylist=js.readList(circuitresponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId=='shanghai')].Location.country");
		Assert.assertTrue(countrylist.contains("China"));
		
//		.then().log().all()
//		.assertThat()
//		.statusCode(APIHttpStatus.OK_200.getCode())
;	}
}
