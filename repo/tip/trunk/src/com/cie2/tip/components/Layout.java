package com.cie2.tip.components;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Session;

import com.cie2.tip.Visit;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.User.AccessLevel;
import com.cie2.tip.pages.Start;



@IncludeStylesheet("context:style.css")
public class Layout {

	static Logger logger =
	       Logger.getLogger(Layout.class.getName());
	
	@Inject
	private RequestGlobals _requestGlobals;
	
	Object onActionFromLogOut() {
		Session session = _requestGlobals.getRequest().getSession(false);		
		logger.info("Logging Out User ...");
		
		if (session != null) {
			session.invalidate();
			logger.info("Session invalidated");			
		}
		else {
			logger.info("Session is null");
		}
		
		
		return Start.class;
	}
	
	public Boolean isAdmin() {
		Session session = _requestGlobals.getRequest().getSession(false);		
		Visit visit = (Visit) session.getAttribute("aso:com.cie2.tip.Visit");

		if(visit != null ) {
			User user = visit.getUser();

			if(user != null) {
				if(AccessLevel.Admin.equals(user.getAccessLevel()))
						return true;
			}
		}
			
		return false;
	}	
}
