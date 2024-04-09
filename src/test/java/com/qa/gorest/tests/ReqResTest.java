package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.BaseTest.BaseTest;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class ReqResTest extends  BaseTest  {

	
	@BeforeMethod
	public void RestClientSetup() {
		resclient=new RestClient(prop, baseURI);
	}
	

	@Test
	public void GetAllUSers_REQ_RES_Test() {
		resclient.get(REQ_ENDPOINT+"/2",false, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
;	}
}

