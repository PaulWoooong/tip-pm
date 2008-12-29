package com.cie2.tip.pages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.WeeklyStatistics;
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
	

	public List getChartData(){
		List list = new ArrayList();
		List userList = _userManagement.getActiveUser();

		Calendar cal = Calendar.getInstance();
		cal.set(2008, 10, 10);
		Date startDate = cal.getTime();
// kenapa ngaco dapet weeknya ? 
		cal.set(2008, 11, 25);
		Date endDate = cal.getTime();
		
		for (Iterator iter = userList.iterator(); iter.hasNext();) {
			User user = (User) iter.next();
			list.add(user.getUsername());
			List userStatistics = _statisticsService.getStatistics(user, startDate, endDate);
			list.add(userStatistics.size() + 3);
			for (int i=0; i<3; i++) {
				list.add(40 + i);
				list.add(2008);
				list.add(3);
			}
			
			for (Iterator iterator = userStatistics.iterator(); iterator.hasNext();) {
				WeeklyStatistics ws = (WeeklyStatistics) iterator.next();
				list.add(ws.getWeek());
				list.add(ws.getYear());
				list.add(ws.getPoint());
			}


		}

		
		return list;
//	    return new String[]{"abangkis","apit","waddh", "topik"};
	}
	
	public int[] getpopupSize(){
	    return new int[]{800,600};
	}

	public List getActiveUser() {
		// kasih parameter project buat multi project		
		return _userManagement.getActiveUser();
		
	}
}
