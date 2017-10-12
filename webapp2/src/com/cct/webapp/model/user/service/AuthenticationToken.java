package com.cct.webapp.model.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import com.cct.webapp.model.user.domain.UserInfo;

public class AuthenticationToken {

	
	public static String generateToken(UserInfo user){
		String tokenServer = null;
		
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.DATE, 3);
			date = c.getTime();
			
			tokenServer = Jwts.builder()
							.setIssuer("Mr.Kaido Kaoru")
							.setSubject("Authen token server")
							.claim("name", user.getName())
							.claim("surname", user.getSurname())
							.claim("dob", user.getDob())
							.claim("sex", user.getSex())
							.claim("username", user.getUsername())
							.claim("password", user.getPassword())
							.setIssuedAt(Date.from(Instant.now()))
							.setExpiration(date)
							.signWith(
								SignatureAlgorithm.HS256,
								"secret".getBytes("UTF-8")  
							).compact();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenServer;
	}
	
	public static UserInfo verifyToken(String tokenServer){
		UserInfo userInfo = null;
		try {
			
			Claims claims = Jwts.parser()
				       //.setSigningKey(DatatypeConverter.parseBase64Binary(SignatureAlgorithm.HS256.toString()))
				       .setSigningKey("secret".getBytes("UTF-8"))
				       .parseClaimsJws(tokenServer).getBody();
				   
			userInfo = new UserInfo();
			userInfo.setName((String)claims.get("name"));
			userInfo.setSurname((String)claims.get("surname"));
			userInfo.setDob((String)claims.get("dob"));
			userInfo.setSex((String)claims.get("sex"));
			userInfo.setUsername((String)claims.get("username"));
			userInfo.setPassword((String)claims.get("password"));
			
			System.out.println("ID: " + claims.getId());
		    System.out.println("Subject: " + claims.getSubject());
		    System.out.println("Issuer: " + claims.getIssuer());
		    System.out.println("Expiration: " + claims.getExpiration());
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}
}
