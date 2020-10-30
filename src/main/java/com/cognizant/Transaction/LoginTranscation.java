package com.cognizant.Transaction;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.cognizant.Util.JSONUtil;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.persistence.PersonalData;

@Default
/*
 * 							login transaction is the business transaction 
 */
@Transactional(SUPPORTS)
public class LoginTranscation implements LoginTransactionInterface{
	
	
	@Inject
	JSONUtil util; 
	
	
	@PersistenceContext(unitName ="personaldata")
	private EntityManager manager; 
	
	//private Long ID; 
	



	public boolean loggingin (String username, String password)
	{
		// redirect user after log in  backend or only front end 
		///https://stackoverflow.com/questions/42061943/how-should-i-send-my-username-and-password-to-backend-in-java
	
		String string_querys  = "SELECT c FROM PersonalData c WHERE c.username = :id AND c.password = :pass"; 
		boolean autheticate=false;
		TypedQuery<PersonalData>  query = manager.createQuery(string_querys, PersonalData.class);
		query.setParameter("id", username);
	    query.setParameter("pass", password);
	  
	    PersonalData user = query.getSingleResult(); 
	    
	    try {
	    if (user == null)
	    {
	    	autheticate=true; 
	    }
	    else 
	    {
	    	throw new Exception ("Validation failed!");
	    }
	    }
	    catch (Exception ex )
	    {
	    
	    }  
	    return autheticate; 	
	}
	
	@Transactional(REQUIRED)
	public String findallaccounts ()
	{
		String sql_string = "SELECT a FROM PersonalData a"; 
		//TypedQuery<PersonalData>  query = manager.createQuery("SELECT a FROM PersonalData a", PersonalData.class);
		//List<PersonalData> allaccounts = reuse.mapper(sql_string).getResultList(); 
		
		return null; 
		
	}
	@Transactional(REQUIRED)
	public PersonalData findUser(String username)
	{
		String query = "SELECT c FROM PersonalData c WHERE c.username = :id"; 
		TypedQuery<PersonalData> query_data = manager.createQuery(query, PersonalData.class );
		query_data.setParameter("id", username);
		
		PersonalData data = query_data.getSingleResult(); 
		
		return data; 
	}
	
}
