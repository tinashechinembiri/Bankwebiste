package com.cognizant.Transaction;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.json.JSONObject;

import com.cognizant.Util.JSONUtil;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.persistence.PersonalData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class HomepageTransaction implements HomepageTransactionInterface {
	
	@PersistenceContext(unitName ="personaldata")
	
	private EntityManager manager;
	
	@Inject
	JSONUtil util; 
	ReuasableUtil reuse; 
	 	
	public PersonalData find_single_user(Long id) 
	{
		
		return manager.find(PersonalData.class, id); 
	}
	@Transactional(REQUIRED)
	public PersonalData find_account_details (String username)
	{
	
		PersonalData data_1 = findUser(username); 
		String full_join = "SELECT c FROM PersonalData c INNER JOIN c.account a WHERE a.account_number = :p_id"; 
		TypedQuery<PersonalData> query = manager.createQuery(full_join, PersonalData.class);
		 
		query.setParameter("p_id", data_1.getUser_id()); 
		
		PersonalData data = query.getSingleResult(); 
		
		return data; 
	}
	
	public PersonalData findUser(String username)
	{
		String query = "SELECT c FROM PersonalData c WHERE c.username = :username"; 
		TypedQuery<PersonalData> query_data = manager.createQuery(query, PersonalData.class );
		query_data.setParameter("username", username);
		
		PersonalData data = query_data.getSingleResult(); 
		
		return data; 
	}
	
	@Transactional(REQUIRED)
	public String createUser(String newUser)
	{
        Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        
        PersonalData create_user = gson.fromJson(newUser, PersonalData.class); 
      
		if (find_single_user(create_user.getId()) == null)
		{
			manager.persist(create_user);
			return newUser; 
		}
		return  "{\"Message\":\"user already exist\"}";
		
	}
	@Transactional(REQUIRED)
	public String findallaccounts ()
	{
		this.reuse = new ReuasableUtil (this.manager); 
		String sql_string = "SELECT a FROM PersonalData a";
		List<PersonalData> allaccounts =  this.reuse.mapper(sql_string).getResultList(); 
		allaccounts.forEach(action ->{
			System.out.println(action.toString());
		});
		return   util.getJSONForObject(allaccounts); 
	}
	@Transactional(REQUIRED)
	public String updatepassword(long username, String newPassword  )
	{
		JSONObject jsonObject = new JSONObject(newPassword);
		String password = jsonObject.getString("password"); 
		PersonalData updateuser = this.find_single_user(username); 
		if (newPassword != null)
		{
			updateuser.setPassword(password);
			manager.merge(updateuser); 	
		}
		
		return "{\\\"Message\\\":\\\"Password updated \\\"}"; 
	}	
}
