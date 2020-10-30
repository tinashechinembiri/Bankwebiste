package com.cognizant.Transaction;

import static javax.transaction.Transactional.TxType.REQUIRED;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;




import com.cognizant.persistence.PersonalData;
import com.cognizant.persistence.Statement;


public class moneytransaction implements moneytransactioninterface{
	
	@PersistenceContext(unitName ="personaldata")
	private EntityManager manager;
	
	
	@Transactional(REQUIRED)
	public String moneytranfer (double transferamount , long transferaccountid, long userid  ) {

		
		PersonalData useraccount = this.deduct(userid, transferamount);
		 
		PersonalData transferaccount = this.induct(transferaccountid,transferamount); 
			
			if (useraccount != null && transferaccount !=null )
			{
				manager.merge(useraccount); 
				manager.merge(transferaccount); 	
				
			}
			else 
			{
				return "{\"Money\":\"failed\"}"; 
			}	
		return "{\"Money\":\"Sent\"}"; 
	}
	@Transactional(REQUIRED)
	public String Statement (double transferamount , long transferaccountid, long userid)
	{
			PersonalData statement;
			statement = this.createStatement(transferaccountid, transferamount, "incoming");
			PersonalData  userStatement = this.createStatement(userid, transferamount, "outgoing"); 
			this.manager.persist(statement); 
			this.manager.persist(userStatement);		
		return "{\"statment\":\"added\"}"; 
	}
	
	private PersonalData deduct (long id, double transferamount)
	{
		PersonalData useraccount = this.finddata(id);
		useraccount.getAccount().stream().distinct()
		.filter(value -> value.getBalance()>= transferamount )
		.map( s -> {
			
			
			double newbalance = s.getBalance() - transferamount; 
			s.setBalance(newbalance);	
			return s; 
		})
		.collect(Collectors.toList()); 
		return useraccount; 
	}
	
	private PersonalData induct (long id, double transferamount)
	{
		PersonalData useraccount = this.finddata(id);
		useraccount.getAccount().stream().distinct()
		.filter(value -> value.getBalance()>= transferamount )
		.map( s -> {
			
			
			double newbalance = s.getBalance() + transferamount; 
			s.setBalance(newbalance);	
			return s; 
		})
		.collect(Collectors.toList()); 
		return useraccount; 
		
	}
	public PersonalData schedulePayment ()
	{
		
		
		
		return null; 
	}
	
	public PersonalData Loan (String userjson)
	{
		
		
		return null ; 
	}
	
	private PersonalData finddata (Long id)
	{
		String query = "SELECT c FROM PersonalData c INNER JOIN c.account a ON c.user_id = a.account_number  WHERE a.type =:p_id AND a.account_number =:p_account";
	
		TypedQuery<PersonalData> query_typed = manager.createQuery(query, PersonalData.class);		
		query_typed.setParameter("p_id", "debit");
		query_typed.setParameter("p_account", id); 
		
			PersonalData data = query_typed.getSingleResult(); 
			/*if (data.getAccount().size() >= 2)
			{
				data.getAccount().remove(1); 
				
			}*/
			
		return data; 
	}
	private  PersonalData find_single_user(Long id) 
	{
		return manager.find(PersonalData.class, id); 
	}
	
	
	private PersonalData createStatement(long id, double transferamount, String type) 
	{
		PersonalData user = find_single_user(id); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   user.getAccount().stream().map(action -> {
			
			  Statement addstatement  = new Statement(UUID.randomUUID().toString(), action.getCardnumber(),transferamount,dtf.format(now), 
					  type);
			  action.getStatement().add(addstatement); 		
			  
			  System.out.println(action.toString());
			return action; 
		}) 
		.collect(Collectors.toList());
		
		return user; 
	
	}
	

}
