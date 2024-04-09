package com.qa.gorest.RestClient;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	
	
	//private static final String BASE_URI="https://gorest.co.in";
	//private static final String BEARER_TOKEN="72f4ac6e27a57865323f215b582ddf1ca905537496adcda1068ae87ba9175ff5";
	
	private static RequestSpecBuilder specBuilder;
	
	//static {
	//	specBuilder = new RequestSpecBuilder();
	//}
	private String baseURI;
	private Properties prop;
	private boolean isAuthDone =false;
	
	
	public RestClient(Properties prop,String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.baseURI=baseURI;
		this.prop=prop;
	}
	
	public void addAuthorizationHeader() {
		if(!isAuthDone) {specBuilder.addHeader("Authorization", "Bearer "+ prop.getProperty("tokenID") );
		isAuthDone=true;
		
		}
		
	}


	private void setRequestContentType(String contentType) {
		
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "html":
			specBuilder.setContentType(ContentType.HTML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
			
			
		default:
			System.out.println("Please pass the right content type...." + contentType);
			throw new APIFrameworkException("INVALID CONTENT TYPE");
			
		}
	}
	
	
	private RequestSpecification createRequestspec(boolean Authreq) {
		specBuilder.setBaseUri(baseURI);
		if(Authreq) {	addAuthorizationHeader();}
	
		return specBuilder.build();
	
	}
	
	private RequestSpecification createRequestspec(Map<String, String> headersmap,boolean Authreq) {
		specBuilder.setBaseUri(baseURI);
		if(Authreq) {	addAuthorizationHeader();}
		if(headersmap!=null) {
			specBuilder.addHeaders(headersmap);
		}
		return specBuilder.build();
	
	}
	
	private RequestSpecification createRequestspec(Map<String, String> headersmap,Map<String, String> queryMap,boolean Authreq) {
		specBuilder.setBaseUri(baseURI);
		if(Authreq) {	addAuthorizationHeader();}
		if(headersmap!=null) {
			specBuilder.addHeaders(headersmap);
		}
		if(queryMap!=null) {
			specBuilder.addQueryParams(queryMap);
		}
		return specBuilder.build();
	
	}
	
	
	private RequestSpecification createRequestspec(Object requestBody,String contentType,boolean Authreq) {
		specBuilder.setBaseUri(baseURI);
		if(Authreq) {	addAuthorizationHeader();}
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	
	}
	private RequestSpecification createRequestspec(Map<String, String> headersmap,Object requestBody,String contentType,boolean Authreq) {
		specBuilder.setBaseUri(baseURI);
		if(Authreq) {	addAuthorizationHeader();}
		setRequestContentType(contentType);
		if(headersmap!=null) {
			specBuilder.addHeaders(headersmap);
		}
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		
		
		
		return specBuilder.build();
	
	}
	
	
	///HTTP METHOD UTILS
	///GET CALLS
	public Response get(String serviceURI,boolean Authreq,boolean log) {
		if(log) {
		return RestAssured.given(createRequestspec(Authreq)).log().all()
		    .when().log().all()
		    .get(serviceURI);
		}
	
			return RestAssured.given(createRequestspec(Authreq))
				    .when()
				    .get(serviceURI);
		
		
	}
	
	
	
	public Response get(String serviceURI,Map<String, String> headersmap,boolean Authreq,boolean log) {
		if(log) {
		return RestAssured.given(createRequestspec(headersmap,Authreq)).log().all()
		    .when().log().all()
		    .get(serviceURI);
		}
	
			return RestAssured.given(createRequestspec(headersmap,Authreq))
				    .when()
				    .get(serviceURI);
		
		
	}
	
	

	public Response get(String serviceURI,Map<String, String> headersmap,Map<String, String> queryMap,boolean Authreq,boolean log) {
		if(log) {
		return RestAssured.given(createRequestspec(headersmap ,queryMap,Authreq)).log().all()
		    .when().log().all()
		    .get(serviceURI);
		}
	
			return RestAssured.given(createRequestspec(headersmap,queryMap,Authreq))
				    .when()
				    .get(serviceURI);
			
		
		
	
		
	}
	
	
	///////////////////POST CALLS
	
	public Response post(String serviceURI,Object requestBody,String contentType,boolean Authreq,boolean log) {
	if(log)	{
		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq)).log().all()
		.when().log().all()
		.post(serviceURI);
	}
	
		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
		.when()
		.post(serviceURI);
	}
	
	
	
	
	public Response post(String serviceURI,Map<String, String> headersmap,Object requestBody,String contentType,boolean Authreq,boolean log) {
	if(log)	{
		return RestAssured.given(createRequestspec(headersmap, requestBody,contentType,Authreq)).log().all()
		.when().log().all()
		.post(serviceURI);
	}

		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
		.when()
		.post(serviceURI);
	
	
	}
	
	/////////////////////////PUT CALLS??????????????????????
	public Response put(String serviceURI,Object requestBody,String contentType,boolean Authreq,boolean log) {
	if(log)	{
		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq)).log().all()
		.when().log().all()
		.put(serviceURI);
	}

		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
		.when()
		.put(serviceURI);
	
	
	}
	
	
	public Response put(String serviceURI,Map<String, String> headersmap,Object requestBody,String contentType,boolean Authreq,boolean log) {
	if(log)	{
		return RestAssured.given(createRequestspec(headersmap, requestBody,contentType,Authreq)).log().all()
		.when().log().all()
		.put(serviceURI);
	}
	
		return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
		.when()
		.put(serviceURI);

	
	
	
	}
	///////////////////PATCH?????????????????????????????
	public Response patch(String serviceURI,Object requestBody,String contentType,boolean Authreq,boolean log) {
		if(log)	{
			return RestAssured.given(createRequestspec(requestBody, contentType,Authreq)).log().all()
			.when().log().all()
			.patch(serviceURI);
		}
	
			return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
			.when()
			.patch(serviceURI);
		}
		
		
		
		
		public Response patch(String serviceURI,Map<String, String> headersmap,Object requestBody,String contentType,boolean Authreq,boolean log) {
		if(log)	{
			return RestAssured.given(createRequestspec(headersmap, requestBody,contentType,Authreq)).log().all()
			.when().log().all()
			.patch(serviceURI);
		}

			return RestAssured.given(createRequestspec(requestBody, contentType,Authreq))
			.when()
			.patch(serviceURI);
		}
		
		
		
		////////////////////DELETE
		public Response delete(String serviceURI,boolean Authreq,boolean log) {
			if(log) {
			return RestAssured.given(createRequestspec(Authreq)).log().all()
			    .when().log().all()
			    .delete(serviceURI);
			}
	
				return RestAssured.given(createRequestspec(Authreq))
					    .when()
					    .delete(serviceURI);
			
			
		}
	
	
	
	
	
	
	///////OAtuh 2
		
		
		public String getAccesstoken(String basesURI,String granttype,String clientId,String ClientSecrt) {
			RestAssured.baseURI="https://test.api.amadeus.com";
		String accesstoken=RestAssured.given().log().all()
		.contentType(ContentType.URLENC)
		.formParam("grant_type", granttype)
		.formParam("client_id", clientId)
		.formParam("client_secret", ClientSecrt)
		.when()
		.post(basesURI)
		.then()
		.extract().path("access_token");
		System.out.println(accesstoken);
		System.out.println("222222222222222222222222222222222222222222");
		return accesstoken;
		
		}
	
	
	
	
}
