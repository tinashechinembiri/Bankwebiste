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
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@IdClass(accountcompositekeys.class)
@Table(name="account")
public class Account implements Serializable {
	
	/**
	 * 
	 */
	@Id
	@Column(name = "account_cardnumber", unique = false)
	private Long account_cardnumber;
	@Column(name = "account_number")
	private Long  account_number; 	
	private String type ; 
	private String Description; 
	private double balance; 
	private String credit_line; 
	private String sortcode;
	private Date expirydate;
	
	
	@OneToMany( targetEntity = Statement.class , fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
	@JoinColumn(name="cardnumber",referencedColumnName = "account_cardnumber")
	
	private List<Statement> Statement ;
	
	public Account() {
		super();
		
	}
	
	public Account(Long account_number, Long cardnumber, String type, String description, double balance,
			String credit_line, String sortcode, Date expirydate, List<com.cognizant.persistence.Statement> statement) {
		super();
		
		this.account_number = account_number;
		this.account_cardnumber = cardnumber;
		this.type = type;
		Description = description;
		this.balance = balance;
		this.credit_line = credit_line;
		this.sortcode = sortcode;
		this.expirydate = expirydate;
		this.Statement = statement; 
		
	}
	
	


	public Long getCardnumber() {
		return account_cardnumber;
	}

	public void setCardnumber(Long cardnumber) {
		this.account_cardnumber = cardnumber;
	}

	public List<Statement> getStatement() {
		return Statement;
	}



	public void setStatement(List<Statement> statement) {
		Statement = statement;
	}



	public String getSortcode() {
		return sortcode;
	}
	




	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCredit_line() {
		return credit_line;
	}
	public void setCredit_line(String credit_line) {
		this.credit_line = credit_line;
	}


	public Long getAccount_number() {
		return account_number;
	}


	public void setAccount_number(Long account_number) {
		this.account_number = account_number;
	}
	
	public String toString() {
		return "Account [account_number=" + account_number + ", cardnumber=" + account_cardnumber + ", type=" + type
				+ ", Description=" + Description + ", balance=" + balance + ", credit_line=" + credit_line
				+ ", sortcode=" + sortcode + ", expirydate=" + expirydate + "]";
	}

}
