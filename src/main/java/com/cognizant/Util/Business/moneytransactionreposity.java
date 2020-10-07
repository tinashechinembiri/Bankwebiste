package com.cognizant.Util.Business;

import com.cognizant.persistence.PersonalData;

public interface  moneytransactionreposity {

	public String moneytranfer (double transferamount , long transferaccountid, long userid ); 
	public String Statement (double transferamount , long transferaccountid, long userid); 
}


