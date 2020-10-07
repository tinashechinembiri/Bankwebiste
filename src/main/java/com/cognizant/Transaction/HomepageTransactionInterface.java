package com.cognizant.Transaction;


import com.cognizant.persistence.PersonalData;

public interface HomepageTransactionInterface {
	
	PersonalData find_single_user(Long id); 
	PersonalData find_account_details(String username);
	String createUser(String newUser); 
	String findallaccounts ();
	String updatepassword(long username, String newPassword  ); 
}
