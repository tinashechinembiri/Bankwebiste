package com.cognizant.Util.Business;


import com.cognizant.persistence.PersonalData;

public interface Homepage_Reposity {
	
	PersonalData find_account_details (String username ); 
	String createUser(String newUser); 
	String findallaccounts (); 
	String updatepassword(long username, String newPassword); 

}
