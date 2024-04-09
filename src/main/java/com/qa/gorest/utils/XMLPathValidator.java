package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathValidator {

	
	
	
	private XmlPath Getresponsebody(Response res) {
		String resbody= res.body().toString();
		return new XmlPath(resbody);
	}
	
	
	
	
	public <T> T read(Response res,String deepscanXMLPath) {
		
		XmlPath xm= Getresponsebody(res);
		
		return xm.get(deepscanXMLPath);
	}
	
	
	
	public <T> List<T> readList(Response res,String deepscanXMLPath) {
		
		XmlPath xm= Getresponsebody(res);
		
		return xm.getList(deepscanXMLPath);
	}
	
	
	
public <T> List<Map<String ,T>> readListMap(Response res,String deepscanXMLPath) {
		
		XmlPath xm= Getresponsebody(res);
		
		return xm.getList(deepscanXMLPath);
	}
	
	
}
