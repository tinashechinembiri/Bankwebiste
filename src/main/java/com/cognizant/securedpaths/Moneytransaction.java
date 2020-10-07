package com.cognizant.securedpaths;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.cognizant.Util.Business.moneytransactionreposity;
import com.cognizant.persistence.PersonalData;

//@Path ("/secured")
@Path ("/test")
public class Moneytransaction {
@Inject
private moneytransactionreposity transaction ; 
@Path("/test1/")
@PUT
@Produces({"application/json"})
public String moneytransfer(String moneytransfer)
{
	
	
	JSONObject jsonObject = new JSONObject(moneytransfer);
	long userid  = jsonObject.getLong("userid"); 
	long  transferaccountid = jsonObject.getLong("transferaccountid"); 
	double transferamount =  jsonObject.getDouble("transferamount"); 
	
	System.out.println(transferaccountid);
	transaction.moneytranfer(transferamount, transferaccountid, userid); 
	//System.out.println(userid);
	
	
	return null; 
}
@Path("/test2/")
@POST
@Produces({"application/json"})
public String createstatement (String moneytransfer)
{
	JSONObject jsonObject = new JSONObject(moneytransfer);
	long userid  = jsonObject.getLong("userid"); 
	long  transferaccountid = jsonObject.getLong("transferaccountid"); 
	double transferamount =  jsonObject.getDouble("transferamount"); 
	
	transaction.Statement(transferamount, transferaccountid, userid); 
	return null; 
}

}
