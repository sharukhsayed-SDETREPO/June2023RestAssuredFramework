package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.BaseTest.BaseTest;
import com.qa.gorest.POJO.User;
import com.qa.gorest.RestClient.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.Excel;
import com.qa.gorest.utils.StringUtils;

public class APISchemaValidatortest extends BaseTest{

	@BeforeMethod
	public void RestClientSetup() {
		resclient=new RestClient(prop, baseURI);
	}
	
	
	
	@DataProvider
	public Object[][] createuserdata_exCELDATA() {
		Object [][] data=Excel.GetExcelUtilData(APIConstants.CreateUser_DATA, "Sheet1");
		return data;
	}
	
	
@Test(dataProvider = "createuserdata_exCELDATA")
	
	public void Createuser_Test_UserExcel_andvalidateJSONSCHEMAVALIDATOR(String name,String gender,String status) {
	    User usr=   new User(name, StringUtils.generateRanddomeEmail(), gender, status);
	//	RestClient	resclient1= new RestClient(prop, baseURI);
	   	resclient.post(GOREST_ENDPOINT, usr, "json", true,true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.body(matchesJsonSchemaInClasspath("gorestschema.json"));
	
	}
}
