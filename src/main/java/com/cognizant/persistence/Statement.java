package com.cognizant.persistence;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="statement")
public class Statement  implements Serializable {
	@Id 
	private String referencenumber; 
	
	
	@Column(name = "cardnumber", unique = false)
	private Long  cardnumber;
	
	private double balance ; 
	
	private String uodatedate; 
	
	private String statementtype;
	
	
	public Statement()
	{
		
	}

	public Statement(String referencenumber, Long cardnumber, double balance, String uodatedate, String statementtype) {
		super();
		this.referencenumber = referencenumber;
		
		this.cardnumber = cardnumber;
		this.balance = balance;
		this.uodatedate = uodatedate;
		this.statementtype = statementtype;
	}
	
	//@ManyToOne
	//@JoinColumn(name="cardnumber",insertable = false, updatable = false)
	//private Account account ; 


	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUodatedate() {
		return uodatedate;
	}
	public void setUodatedate(String string) {
		this.uodatedate = string;
	}
	public String getReferencenumber() {
		return referencenumber;
	}
	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}
	public String getStatementtype() {
		return statementtype;
	}
	public void setStatementtype(String statementtype) {
		this.statementtype = statementtype;
	}
	public Long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	@Override
	public String toString() {
		return "Statement [referencenumber=" + referencenumber + ", cardnumber=" + cardnumber + ", balance=" + balance
				+ ", uodatedate=" + uodatedate + ", statementtype=" + statementtype + "]";
	}

}
