package com.cie2.tip.components;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Session;

import com.cie2.tip.Visit;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.User.AccessLevel;
import com.cie2.tip.pages.Start;
import com.cie2.tip.services.LayoutInfoNotifier;



@IncludeStylesheet("context:style.css")
public class Layout {

	static Logger logger =
	       Logger.getLogger(Layout.class.getName());
	
	@Inject
	private RequestGlobals _requestGlobals;
	
	@Inject
	private LayoutInfoNotifier layoutInfo;
	
	
	Object onActionFromLogOut() {
		Session tapSession = _requestGlobals.getRequest().getSession(false);		
		logger.info("Logging Out User ...");
		
		if (tapSession != null) {
			tapSession.invalidate();
			logger.info("Session invalidated");			
		}
		else {
			logger.info("Session is null");
		}
		
		
		return Start.class;
	}
	
	public Boolean isAdmin() {
		Session tapSession = _requestGlobals.getRequest().getSession(false);	
		Visit visit = (Visit) tapSession.getAttribute("sso:com.cie2.tip.Visit");
		
		
		if(visit != null ) {
			User user = visit.getUser();

			if(user != null) {
				if(AccessLevel.Admin.equals(user.getCurrentProfile().getAccessLevel()))
						return true;
			}
		}
			
		return false;
	}	
	
	public Boolean isAutoLogin() {
		return layoutInfo.isAutoLoginOn();
	}
	
}
