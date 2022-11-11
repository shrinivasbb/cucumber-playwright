package com.cukespro.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.JsonPath;

public class ObjectRepository {

	String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
			+ "resources" + File.separator + "repository" + File.separator;
	public Map<String, String> pageMap;
	String jsonString;

	public ObjectRepository(String afile, String jsonElem) throws Exception {
		this.pageMap = new Gson().fromJson(new JsonReader(new FileReader(filePath + afile)),
				Map.class);
		this.jsonString = new Gson().toJson(pageMap.get(jsonElem));
	}
	
	public String getSelectorFor(String element) {
		return JsonPath.read(jsonString, "$." + element);
	} 

}