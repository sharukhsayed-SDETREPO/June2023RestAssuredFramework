package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class CreateUser extends  BaseTest{

	@BeforeMethod
	public void RestClientSetup() {
		resclient=new RestClient(prop, baseURI);
	}
	
	
	@DataProvider
	public Object[][] createuserdata() {
		return new Object[] [] {
			{"Subodh","male","active"},
			{"Indira","female","inactive"},
			{"suresh","male","active"}
		};
	}
	
	@Test(dataProvider = "createuserdata")
	
	public void Createuser_Test(String name,String gender,String status) {
	    User usr=   new User(name, StringUtils.generateRanddomeEmail(), gender, status);
	//	RestClient	resclient1= new RestClient(prop, baseURI);
	     Integer id=	resclient.post(GOREST_ENDPOINT, usr, "json", true,true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.extract().path("id");
	System.out.println("Generate ID is " + id);
	//internally if we are calling multiple http calls within a test then always create a spearate restclinet object
	RestClient	resclient13= new RestClient(prop, baseURI);
	resclient13.get(GOREST_ENDPOINT+"/"+id, true,true)
	.then()
	.assertThat()
	.statusCode(APIHttpStatus.OK_200.getCode())
	.and()
	.body("id", equalTo(id) );
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DataProvider
	public Object[][] createuserdata_exCELDATA() {
		Object [][] data=Excel.GetExcelUtilData(APIConstants.CreateUser_DATA, "Sheet1");
		return data;
	}
	
	@Test(dataProvider = "createuserdata_exCELDATA")
	
	public void Createuser_Test_UserExcel(String name,String gender,String status) {
	    User usr=   new User(name, StringUtils.generateRanddomeEmail(), gender, status);
	//	RestClient	resclient1= new RestClient(prop, baseURI);
	     Integer id=	resclient.post(GOREST_ENDPOINT, usr, "json", true,true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.extract().path("id");
	System.out.println("Generate ID is " + id);
	//internally if we are calling multiple http calls within a test then always create a spearate restclinet object
	RestClient	resclient13= new RestClient(prop, baseURI);
	resclient13.get(GOREST_ENDPOINT+"/"+id, true,true)
	.then()
	.assertThat()
	.statusCode(APIHttpStatus.OK_200.getCode())
	.and()
	.body("id", equalTo(id) );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
