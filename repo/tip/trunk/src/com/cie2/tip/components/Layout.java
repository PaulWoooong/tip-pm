package com.cie2.tip.components;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Session;

import com.cie2.tip.pages.Start;



@IncludeStylesheet("context:style.css")
public class Layout {

	static Logger logger =
	       Logger.getLogger(Layout.class.getName());
	
	@Inject
	private RequestGlobals _requestGlobals;
	
	Object onActionFromLogOut() {
//		_logger.info(_visit.getMyLoginId() + " is logging out.");
		Session session = _requestGlobals.getRequest().getSession(false);
		
//		logger.info("Logging out Visit=" + session.getAttribute("aso:com.cie2.tip.Visit"));
		
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
}
