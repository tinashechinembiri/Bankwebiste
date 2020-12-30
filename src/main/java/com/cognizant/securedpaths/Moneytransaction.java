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
@Path ("/secured")
public class Moneytransaction {
@Inject
private moneytransactionreposity transaction ; 
@Path("/moneytransfer/")
@PUT
@Produces({"application/json"})
public String moneytransfer(String moneytransfer)
{
	
	
	JSONObject jsonObject = new JSONObject(moneytransfer);
	long userid  = jsonObject.getLong("userid"); 
	long  transferaccountid = jsonObject.getLong("transferaccountid"); 
	double transferamount =  jsonObject.getDouble("transferamount"); 
	
	System.out.println(transferaccountid);
	String results = transaction.moneytranfer(transferamount, transferaccountid, userid); 
	
	return results; 
}
@Path("/statementupdate/")
@POST
@Produces({"application/json"})
public String createstatement (String moneytransfer)
{
	JSONObject jsonObject = new JSONObject(moneytransfer);
	long userid  = jsonObject.getLong("userid"); 
	long  transferaccountid = jsonObject.getLong("transferaccountid"); 
	double transferamount =  jsonObject.getDouble("transferamount"); 
	
	String result = transaction.Statement(transferamount, transferaccountid, userid); 
	return result; 
}


}
