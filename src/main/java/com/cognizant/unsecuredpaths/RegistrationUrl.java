package com.cognizant.unsecuredpaths;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.cognizant.Util.Business.Homepage_Reposity;

@Path ("/account")
public class RegistrationUrl {
	@Inject
	private Homepage_Reposity mn; 
	
	@Path("/account/registration")
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(String newUser)
	{	
		//JSONObject jsonString = new JSONObject(newUser);
		//String pageTest = jsonString.getJSONObject("registration").toString(); 
		//System.out.println(pageTest);
		return mn.createUser(newUser); 
	}
	
	@Path("/account/Userdata")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findaccount ()
	{
		return mn.findallaccounts(); 
	}
	
	@Path("/account/updatepassword/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String updatepassword( @PathParam("id") long  id, String newPassword)
	{
		return mn.updatepassword(id, newPassword); 
	}
	

	
}
