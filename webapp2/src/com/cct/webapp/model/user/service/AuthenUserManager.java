package com.cct.webapp.model.user.service;

import com.cct.webapp.model.user.domain.UserInfo;

public class AuthenUserManager {

	public UserInfo authenUser(String username, String password){
		
		UserInfo user = null;
		try {
			
			if(!username.isEmpty() && !password.isEmpty()){
				user = new UserInfo();
				user.setName("Kaido");
				user.setSurname("Kaoru");
				user.setDob("13/09/1991");
				user.setSex("Male");
				user.setUsername(username);
				user.setPassword(password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public String gennerateTokenServer(UserInfo user){
		String tokenServer = null;
		
		try {
			
			tokenServer = AuthenticationToken.generateToken(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenServer;
	}
	
	public UserInfo verifyTokenServer(String tokenServer){
		return AuthenticationToken.verifyToken(tokenServer);
	}
}
