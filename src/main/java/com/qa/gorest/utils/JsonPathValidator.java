package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {

	
	private String GetJsonresponse(Response res) {
		return  res.getBody().asString();
	}
	
	
	public <T> T read(Response res,String jsonpath) {
		
		String jsonResponse =  GetJsonresponse(res);
		
		try {
		return JsonPath.read(jsonResponse , jsonpath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException( jsonpath +" is not valid");
		}
	}
	
		public <T> List<T> readList(Response res,String jsonpath) {
			String jsonResponse =  GetJsonresponse(res);
		

		try {
		return JsonPath.read(jsonResponse, jsonpath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIFrameworkException( jsonpath +" is not valid");
		}
	}
	
	
		public <T> List<Map<String, T>> readMapList(Response res,String jsonpath) {
			
			
			String jsonResponse =  GetJsonresponse(res);
			try {
			return JsonPath.read(jsonResponse, jsonpath);
			}
			catch(PathNotFoundException e) {
				e.printStackTrace();
				throw new APIFrameworkException( jsonpath +" is not valid");
			}
		}
	
	
	
	
	
}



