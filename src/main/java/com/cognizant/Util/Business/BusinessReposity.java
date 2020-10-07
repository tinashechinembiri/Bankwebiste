package com.cognizant.Util.Business;

import com.cognizant.persistence.PersonalData;

public interface BusinessReposity {
	
	public String findallaccounts (); 
	public boolean loggingin (String username, String password); 
	public PersonalData findUser(String username); 

}
