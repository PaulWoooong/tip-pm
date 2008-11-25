package com.cie2.tip;

import java.io.Serializable;

import com.cie2.tip.entities.User;


public class Visit implements Serializable{

	private User user;
	
//	private Boolean loggedIn;
//
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Boolean isLoggedIn() {
//		return loggedIn;
//	}
//
//	public void setLoggedIn(Boolean loggedIn) {
//		this.loggedIn = loggedIn;
//	}

	
	
}
