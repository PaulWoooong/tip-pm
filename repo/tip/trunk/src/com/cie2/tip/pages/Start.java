package com.cie2.tip.pages;

import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.Visit;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.SecurityFinder;


public class Start {
	static Logger logger =
	       Logger.getLogger(Start.class.getName());

	@Property
	private String username;
	
	@Property
	private String password;

	@Component(id = "username")
	private TextField _username;
	
	@Component(id="loginForm")
	private Form _form;

	@ApplicationState(create=false)
	private Visit visit;	

//	@InjectPage
//	private Admin admin;
//
	@Inject
	private SecurityFinder securityFinder;
	
	/** @ToDo
	 	seharusnya tugas instatiate visit ada di security finder kah ? 
		masih campur2 antara login biasa dengan autologin, nanti dipikirin lagi
	*/ 
	void onValidateForm() {
		try {
			System.out.println("Authenticating " + username);
			logger.info("Authenticating " + username);
			User user = securityFinder.authenticate(username, password);
			
			if(null != user) {
				visit = new Visit();
				visit.setUser(user);
			}
				
		} catch (LoginException e) {
			_form.recordError(_username, e.getMessage());
			logger.info(_username + " " +  e.getMessage());
		}
	}
	
	Object onSuccess() {
		if (logger.isDebugEnabled()) {
			logger.debug("Success " + visit);
		}

//		if(null == visit) return null;
				
//		logger.info("Access Level " + visit.getUser().getAccessLevel());		
//		if(AccessLevel.Admin.equals(visit.getUser().getAccessLevel())) {
//			return admin;
//		}
//		else 
//			return ListContact.class;
//		
		
		return MyTask.class;
	}

}
