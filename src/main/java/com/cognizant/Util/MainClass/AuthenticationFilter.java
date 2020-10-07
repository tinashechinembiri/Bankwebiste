package com.cognizant.Util.MainClass;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import com.cognizant.Util.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import javax.ws.rs.Priorities;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_SCHEME = "Bearer ";

	 @Context
	    private ResourceInfo resourceInfo;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader =requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		//System.out.println(authorizationHeader);
		Method method = resourceInfo.getResourceMethod();
		UriInfo info = requestContext.getUriInfo();
	    if (info.getPath().contains("secured")) {
	    	
		if(!method.isAnnotationPresent(PermitAll.class))
        {
		 
			
			 if(method.isAnnotationPresent(DenyAll.class))
	            {
	                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
	                         .entity("Access blocked for all users !!").build());
	                return;
	            }
			 
			 if (!isTokenBasedAuthentication(authorizationHeader))
			 {
				
				 abortWithUnauthorized(requestContext);
				 return; 
			 }
			 
			 String token = authorizationHeader
                     .substring(AUTHENTICATION_SCHEME.length()).trim();
			
			 try {
				 
				 Verify(token, "Tinashe", requestContext); 
			 }catch (Exception e)
			 {
				 abortWithUnauthorized(requestContext);
			 }
			 
        }
	    }
	}
	
	private boolean isTokenBasedAuthentication(String authorizationHeader)
	{
		
		
		return authorizationHeader != null && authorizationHeader.toLowerCase().startsWith("bearer ");
		
	}
	
	private  void abortWithUnauthorized(ContainerRequestContext requestContext)
	{
		   requestContext.abortWith(
	                Response.status(Response.Status.UNAUTHORIZED)
	                        .header(HttpHeaders.WWW_AUTHENTICATE, 
	                        		Constant.token  + " realm=\"" + requestContext.getHeaderString(HttpHeaders.AUTHORIZATION) + "\"")
	                        .entity("{\"Login\":\"user not logged in\"}")
	                        .build());
	}
	
	public  boolean Verify (String jwttokens, String type, ContainerRequestContext requestContext)
	{
		try{
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			 byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.token);
			    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(jwttokens).getBody();
            	
            
            	
            return claims.getIssuer().equals(type);
            
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException 
                | UnsupportedJwtException | IllegalArgumentException e) {
        	abortWithUnauthorized(requestContext);
            System.out.println(e.getMessage());
            return false;
        }
	
	}
}
