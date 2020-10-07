package com.cognizant.Transaction;

import com.cognizant.persistence.PersonalData;

public interface LoginTransactionInterface {
	
	public  String findallaccounts(); 
	public boolean loggingin (String username, String password); 
	public PersonalData findUser(String username); 
	

}
