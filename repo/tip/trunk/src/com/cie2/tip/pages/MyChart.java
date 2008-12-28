package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.StatisticsService;
import com.cie2.tip.services.UserManagement;

public class MyChart extends CieUserPage {

	@Property
	private User user;

	//services	
	@Inject
	private UserManagement _userManagement;
	
	@Inject 
	private StatisticsService _statisticsService;
	
	public String[] getChart1(){
	    return new String[]{"aa","22","bb","5"};
	}

	public String[] getChart2(){
	    return new String[]{"aa","29","bb","30","cc","10"};
	}
	public int[] getpopupSize(){
	    return new int[]{800,600};
	}

	public List getActiveUser() {
		// kasih parameter project buat multi project

		
		_statisticsService.load(1, 2008, getVisit().getUser().getCurrentProfile());
		return _userManagement.getActiveUser();
		
	}
}
