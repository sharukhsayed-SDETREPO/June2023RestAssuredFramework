package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.BaseTest.BaseTest;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.qameta.allure.Description;

public class GetUser extends BaseTest {

	
	@BeforeMethod
	public void RestClientSetup() {
		resclient=new RestClient(prop, baseURI);
	}
	
	
	
	@Description("THis is for description from allure ")
	@Test(enabled =true ,priority =1, description = "Thid is coming from testng")
	public void GetAllUSers_Test() {
		resclient.get(GOREST_ENDPOINT,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
;	}
	
	
	
	
	@Test(enabled =true ,priority =2)
	public void GetSingleUSers_Test() throws InterruptedException {
	//	Thread.sleep(100000);
		//RestClient	resclient1= new RestClient(prop, baseURI);
		resclient.get(GOREST_ENDPOINT+"/6751939", true,true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.and()
		.body("id", equalTo(6751939))
;	}
	
	@Test(enabled =true ,priority =3)
	public void GetSingleUSerswithqueryPArams_Test()   {
	 Map<String,String> queryparams=new HashMap<>();
	 
	 queryparams.put("name", "naveen");
	 queryparams.put("status", "active");
	//	RestClient	resclient3= new RestClient(prop, baseURI);
	 resclient.get(GOREST_ENDPOINT+"/", null, queryparams,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
	
;	}
	
	
	
	
	
	
}
