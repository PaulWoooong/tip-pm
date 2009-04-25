package com.cie2.tip.services;

import java.util.Date;

import javax.security.auth.login.LoginException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.User;


public class LoginService {
	
	private Session _session;
	
	public LoginService(Session session) {
		_session = session;
	}

	public User authenticate(String username , String password) throws LoginException{
		if("".equalsIgnoreCase(username) || "".equalsIgnoreCase(password))
			throw new LoginException("Empty Argument for login");			
		
		User user = (User) _session.createCriteria(User.class).add(
				Restrictions.eq("username", username)).add(
				Restrictions.eq("password", password)).uniqueResult();

		if(null == user)
			throw new LoginException("Failed To Login");
		if(null == user.getCurrentProfile() )
			throw new LoginException("Current Profile is not set");
		
		return user;
	}
	
	public User findUser(Long userId) {
		return (User) _session.get(User.class, userId);
	}
	
	public void login(User user) {
		user.setLastLogin(new Date());
		// bisa tambahin status isLogin
		_session.merge(user);		
		_session.flush();
	}
}
