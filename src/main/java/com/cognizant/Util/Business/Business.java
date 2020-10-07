package com.cognizant.Util.Business;

import javax.inject.Inject;

import com.cognizant.Transaction.LoginTransactionInterface;
import com.cognizant.persistence.PersonalData;

public class Business implements BusinessReposity {
	@Inject 
	private LoginTransactionInterface login;
	
	public String findallaccounts ()
	{
		return login.findallaccounts(); 
	}
	public boolean loggingin (String username, String password)
	{
		return login.loggingin(username, password); 
	}
	public PersonalData findUser(String username)
	{
		return login.findUser(username); 
	}
}
