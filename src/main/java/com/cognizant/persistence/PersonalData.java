package com.cognizant.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="personaldata")
public class PersonalData implements Serializable {
	
	@Id 
	@Column(name = "user_id")
	private Long  user_id;
	private Long id ; 
	private String  firstName; 
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Column (length = 100)
	private String secondName;
	private String username; 
	private String password;
	
	 	
@OneToMany(targetEntity=Account.class,cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}, fetch = FetchType.LAZY) 
@JoinColumn(name="account_number", referencedColumnName = "user_id")
private List<Account> account ; 


	@Override
public String toString() {
	return "PersonalData [user_id=" + user_id + ", id=" + id + ", firstName=" + firstName + ", secondName=" + secondName
			+ ", username=" + username + ", password=" + password + ", account=" + account + "]";
}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
		

	public PersonalData()
	{
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
