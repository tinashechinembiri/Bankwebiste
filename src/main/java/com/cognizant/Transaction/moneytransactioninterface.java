package com.cognizant.Transaction;

import com.cognizant.persistence.PersonalData;

public interface moneytransactioninterface {
	
	public String moneytranfer (double transferamount , long transferaccountid, long userid  ); 
	public String Statement (double transferamount , long transferaccountid, long userid); 
	

}
