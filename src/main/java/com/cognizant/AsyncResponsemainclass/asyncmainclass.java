package com.cognizant.AsyncResponsemainclass;
import com.cognizant.Util.Business.Homepage_Reposity;
import com.cognizant.persistence.PersonalData;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
@Path("/aysnc")
public class asyncmainclass {
	
	@Path("/account/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void findalldata(@Suspended final  AsyncResponse response) {
		String name = "class"; 
		//mn.findallaccounts();
		response.resume(name); 
	}
	

}
