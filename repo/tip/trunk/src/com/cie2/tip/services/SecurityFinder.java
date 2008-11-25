package com.cie2.tip.services;

import javax.security.auth.login.LoginException;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.User;


public class SecurityFinder {
	
	private Session _session;
	
	public SecurityFinder(Session session) {
		_session = session;
	}

	public User authenticate(String username , String password) throws LoginException{
		if("".equalsIgnoreCase(username) || "".equalsIgnoreCase(password))
			throw new LoginException("Empty Argument for login");			
		
		User user = (User) _session.createCriteria(User.class).add(
				Restrictions.eq("username", username)).add(
				Restrictions.eq("password", password)).uniqueResult();

		if(user == null)
			throw new LoginException("Failed To Login");

		return user;
	}
	
	public User findUser(Long userId) {
		return (User) _session.get(User.class, userId);
	}
}
