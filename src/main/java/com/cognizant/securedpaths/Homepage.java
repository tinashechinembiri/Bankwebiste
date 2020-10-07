package com.cognizant.securedpaths;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.cognizant.Util.Business.Homepage_Reposity;
import com.cognizant.persistence.PersonalData;

@Path ("/secured")
public class Homepage {
	
	@Inject
	private Homepage_Reposity mn; 
	@Path("/account/{username}")
	@GET
	@Produces({"application/json"})
	public PersonalData findalldata(@PathParam("username") String username)
	{
		//		return mn.find_account_details(id).stream().map(e -> e.toString()).collect(Collectors.toList()); 
	//	mn.find_account_details(id).stream().map(e -> e.toString()).reduce("", String::concat);
		
		return  mn.find_account_details(username); 
		
	}
	

}
