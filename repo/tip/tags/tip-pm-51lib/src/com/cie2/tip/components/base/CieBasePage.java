package com.cie2.tip.components.base;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.Visit;
import com.cie2.tip.entities.User;
import com.cie2.tip.pages.Start;
import com.cie2.tip.services.LayoutInfoNotifier;
import com.cie2.tip.services.LoginService;


public class CieBasePage {

	static Logger logger =
	       Logger.getLogger(CieBasePage.class.getName());
	
	@ApplicationState(create=false)
	private Visit visit;
		
	@Inject
	private LoginService loginService;
	
	@Inject
	private LayoutInfoNotifier layoutInfo;
	
//	/**
//	 * Validate that the user is logged in.  If not logged in, then redirects to the login page.
//	 */
	Object onActivate() {
		
		if (null == visit) {
			// check autologin nyala gak ?
			if (layoutInfo.isAutoLoginOn()) {
				autoLogin(1L);
			}
			else 
				return Start.class;
		}
		return null;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	/**
	 * Automatically logs you in as the given user. Its intention is to prevent you being thrown out of the application 
	 */
	private void autoLogin(Long userId) {
		try {
			User user = loginService.findUser(userId);

			visit = new Visit();
			visit.setUser(user);
			logger.info(user.getUsername() + " has been auto-logged-in.");

		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	
}
