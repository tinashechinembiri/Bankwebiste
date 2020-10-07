package com.cognizant.Util.Business;

import javax.inject.Inject;

import com.cognizant.Transaction.moneytransactioninterface;
import com.cognizant.persistence.PersonalData;

public class moneytransactionbusiness implements moneytransactionreposity{
	@Inject 
	moneytransactioninterface transaction ; 
	
	public  String moneytranfer (double transferamount , long transferaccountid, long userid )
	{
		return transaction.moneytranfer(transferamount, transferaccountid, userid); 
	}
	public String Statement (double transferamount , long transferaccountid, long userid)
	{
		return transaction.Statement(transferamount, transferaccountid, userid); 
	}

}
