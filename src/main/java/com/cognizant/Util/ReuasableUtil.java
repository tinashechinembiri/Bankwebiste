package com.cognizant.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.json.JSONObject;

import com.cognizant.persistence.PersonalData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public  class  ReuasableUtil {
	@PersistenceContext(unitName ="personaldata")
	private   EntityManager manager;
	public ReuasableUtil()
	{
		
	}
	
	public ReuasableUtil (EntityManager manager )
	{
		this.manager  = manager; 
	}
	public static String  maptojson (PersonalData data )
	{
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		map.put("account", data.getUser_id()); 
		
		ObjectMapper  json = new ObjectMapper ();
		
		String items;
		try {
			items = json.writeValueAsString( map );
			return items;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		 return null; 
	}
	
	
	public  TypedQuery<PersonalData> mapper (String sql_string )
	{
		System.out.println(sql_string);
		TypedQuery<PersonalData>  query = manager.createQuery(sql_string , PersonalData.class);
		//System.out.println(query.getSingleResult());
		return query; 
	}
	public TypedQuery<PersonalData> mapper (String sql_string , String  unique_value )
	{ 
		TypedQuery<PersonalData> query_data = manager.createQuery(sql_string, PersonalData.class );
		query_data.setParameter("username", unique_value) ; 
		return query_data ; 
	}
	{
		
	}
	

}
