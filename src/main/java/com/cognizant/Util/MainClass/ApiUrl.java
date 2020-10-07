package com.cognizant.Util.MainClass;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.cognizant.Util.Business.BusinessReposity;
import com.cognizant.Util.Business.Homepage_Reposity;
import com.cognizant.persistence.PersonalData;


 
@Path ("/classroom")
public class ApiUrl {
	
	@Inject
	private Homepage_Reposity mn; 
	@Path("/registrationaccount/{username}")
	@GET
	@Produces({"application/json"})
	public PersonalData findalldata(@PathParam("username") String  Username)
	{
		//		return mn.find_account_details(id).stream().map(e -> e.toString()).collect(Collectors.toList()); 
	//	mn.find_account_details(id).stream().map(e -> e.toString()).reduce("", String::concat);
		return  mn.find_account_details(Username); 
		//"{\\\"message\\\":}\\\"succesfully removed\\\"";
	}

/*	
	@Path("/registerlastname")
	@GET
	@Produces({"application/json"})
	return"{\\\"message\\\":}\\\"succesfully removed\\\"";
*/
}
