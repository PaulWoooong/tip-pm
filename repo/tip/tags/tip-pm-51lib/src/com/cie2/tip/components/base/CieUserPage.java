package com.cie2.tip.components.base;

import com.cie2.tip.pages.Start;


public class CieUserPage extends CieBasePage{

	Object onActivate() {
		super.onActivate();
		

		
		if (null == getVisit()) {
			return Start.class;
		}
//		else {
//			if (!AccessLevel.Admin.equals(getVisit().getUser().getAccessLevel()) && 
//				!AccessLevel.User.equals(getVisit().getUser().getAccessLevel())) 
//			{
//				return Start.class;
//			}
//		}

		return null;
	}

	
}
