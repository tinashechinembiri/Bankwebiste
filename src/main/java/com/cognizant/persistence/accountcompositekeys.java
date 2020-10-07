package com.cognizant.persistence;

import java.io.Serializable;

public class accountcompositekeys  implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private long account_number; 
private int cardnumber; 


public accountcompositekeys()

{
	

	

	
}


public accountcompositekeys(long account_number, int cardnumber) {
	super();
	this.account_number = account_number;
	this.cardnumber = cardnumber;
}


public long getAccount_number() {
	return account_number;
}


public void setAccount_number(long account_number) {
	this.account_number = account_number;
}


public int getCardnumber() {
	return cardnumber;
}


public void setCardnumber(int cardnumber) {
	this.cardnumber = cardnumber;
}
	
@Override
public boolean equals(Object object) {
    if (object instanceof accountcompositekeys) {
    	accountcompositekeys pk = (accountcompositekeys)object;
        return account_number == pk.account_number && cardnumber == pk.cardnumber;
    } else {
        return false;
    }
}
@Override
public int hashCode() {
    return (int) (account_number + cardnumber);
}


}
