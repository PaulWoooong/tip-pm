package com.cie2.tip.components.base;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.Visit;
import com.cie2.tip.entities.User;
import com.cie2.tip.pages.Start;
import com.cie2.tip.services.SecurityFinder;


public class CieBasePage {

	static Logger logger =
	       Logger.getLogger(CieBasePage.class.getName());
	
	@ApplicationState(create=false)
	private Visit visit;
		
	@Inject
	private SecurityFinder securityFinder;
	
	
//	/**
//	 * Validate that the user is logged in.  If not logged in, then redirects to the login page.
//	 */
	Object onActivate() {
		
		
		logger.info("Visit : " + visit );
		
		if (null == visit) {
			// check autologin nyala gak ?
			if (isAutoLoginOn()) {
				autoLogin(1L);
			}
			else 
				return Start.class;
		}

		logger.info(" current access level " + visit.getUser().getAccessLevel());
		return null;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	/**
	 * Checks the value of system property jumpstart.auto-login.  If "true" then returns true; if "false" then return false; 
	 * if not set then returns false.
	 */
	private boolean isAutoLoginOn() {
		boolean autoLogin = true;
		return autoLogin;
	}

	/**
	 * Automatically logs you in as the given user. Its intention is to prevent you being thrown out of the application 
	 */
	private void autoLogin(Long userId) {
		try {
			User user = securityFinder.findUser(userId);

			visit = new Visit();
			visit.setUser(user);
			logger.info(user.getUsername() + " has been auto-logged-in.");

		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	
}
