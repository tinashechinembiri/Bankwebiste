package com.cognizant.unsecuredpaths;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import com.cognizant.Util.Constant;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.Util.Business.BusinessReposity;
import com.cognizant.persistence.Credentials;
import com.cognizant.persistence.PersonalData;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Path ("/authentication")
public class UserAuthentication {
	
	@Inject 
	private BusinessReposity business; 

	@POST
	@Path("/login")
	@Produces({"application/json"})
	
	public Response authenticateUser(Credentials credentials)
	{
		String username = credentials.getUsername();
	    String password = credentials.getPassword();    
	    
	    System.out.println("user identity : " + username+":: "+ password);
	    try {
			SignIn(username, password);
			String token = issueToken(username, "LoginToken"); 
			PersonalData data = business.findUser(username); 
			
			return Response.ok(token).header(AUTHORIZATION, "Bearer "+ token).entity(ReuasableUtil.maptojson(data)).build(); 
			
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).entity("{\"message\":\"The Login for this user is invalid'"+ e.getMessage()+"'\"}").build(); 
		} 
	    
	
		
	}
	
	private void SignIn(String username, String password )throws Exception
	{
		byte[] pass_Sauth = Base64.getDecoder().decode(password);
		System.out.println(new String (pass_Sauth));
		
		if (business.loggingin(username, new String (pass_Sauth))!= true )
		{
				System.out.println("its working ");
		}
		else
		{
			throw new Exception ("login failed!");
		}
	}
	
	
	private String issueToken(String username, String subject)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 	long nowMillis = System.currentTimeMillis();
		 	
		    Date now = new Date(nowMillis);
		    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.token);
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		    Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS); 
		    JwtBuilder builder = Jwts.builder().setId(username)
		            .setIssuedAt(now)
		            .setSubject(subject)
		            	.setIssuer("Tinashe")
		            	.setIssuedAt(Date.from(issuedAt))
		            	.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
		            	
		            .signWith(signatureAlgorithm, signingKey);    
		return builder.compact(); 
	}
	public static boolean Verify (String jwttokens, String type)
	{
		try{
            Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.token))
                .parseClaimsJws(jwttokens).getBody();
            return claims.getIssuer().equals(type);
            
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException 
                | UnsupportedJwtException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
	}
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.token))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	 private Date toDate(LocalDateTime localDateTime) {
	        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	    }

}
