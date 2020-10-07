package com.cognizant.Util.Business;
import javax.inject.Inject;
import com.cognizant.Transaction.HomepageTransactionInterface;
import com.cognizant.persistence.PersonalData;

public class Homepage_Bussiness implements Homepage_Reposity  {
	@Inject
	HomepageTransactionInterface api ; 
	public PersonalData find_account_details (String  username )
	{
		return api.find_account_details(username); 
	}
	
	public String createUser(String newUser)
	{
		return api.createUser(newUser); 
	}
	public String findallaccounts ()
	{
		return api.findallaccounts(); 
	}
	public String updatepassword(long username, String newPassword  )
	{
		return api.updatepassword(username, newPassword); 
	}
}
