package com.cognizant.Util;

import java.lang.reflect.Type;
import java.util.List;

import com.cognizant.persistence.PersonalData;
import com.google.gson.Gson;

public class JSONUtil {
	
	private static Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public  <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		
		return gson.fromJson(jsonString, clazz);
	}

	

}
