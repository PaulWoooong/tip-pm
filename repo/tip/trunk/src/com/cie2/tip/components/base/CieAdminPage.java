package com.cie2.tip.components.base;

import com.cie2.tip.entities.User.AccessLevel;
import com.cie2.tip.pages.Start;


public class CieAdminPage extends CieBasePage{

	Object onActivate() {
		super.onActivate();
		
				
		if (null == getVisit()) {
			return Start.class;
		}
		else {
			if(!AccessLevel.Admin.equals(getVisit().getUser().getCurrentProfile().getAccessLevel())) {
				return Start.class;
			}
		}

		return null;
	}

}
