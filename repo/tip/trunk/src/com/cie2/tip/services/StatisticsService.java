package com.cie2.tip.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserProfile;
import com.cie2.tip.entities.WeeklyStatistics;

public class StatisticsService {
	static Logger logger = 
		Logger.getLogger(StatisticsService.class.getName());

	private Session _session;
	
	public StatisticsService(Session session) {
		_session = session;
	}
	
	public WeeklyStatistics load(Integer week, Integer year, UserProfile up) {
		WeeklyStatistics ws = (WeeklyStatistics) _session.createCriteria(WeeklyStatistics.class)
				.add(Restrictions.eq("week", week))
				.add(Restrictions.eq("year", year))
				.add(Restrictions.eq("userProfile", up))
				.uniqueResult();

		// Create the week if fall in our time constrains
		if(null == ws) {
			DateTime dt = new DateTime();

			if(dt.weekOfWeekyear().get() >= week) { // year blom masuk constrain
				logger.info("Creating Week: " + week + " of year: " + year);
				
				ws = new WeeklyStatistics();
				
				ws.setWeek(week);
				ws.setYear(year);
				ws.setUserProfile(up);
				ws.setPoint(0);
				ws.setTaskCreated(0);
				ws.setTaskTaken(0);
				ws.setTaskFinished(0);
				
				_session.persist(ws);
				_session.flush();
			}
		}		

		return ws;
	}
	
	public void nextWeek() {
		
	}
	
	public void getWeek() {
		
	}
	
	public void createTask(TaskItem taskItem, User user) {
		DateTime dt = new DateTime();

		WeeklyStatistics ws = load(dt.getWeekOfWeekyear(), dt.getYear(), user
				.getCurrentProfile());

		ws.setTaskCreated(ws.getTaskCreated() +1);
		
		_session.merge(ws);
		_session.flush();
	}
	
	public void takeTask(TaskItem taskItem, User user) {
		DateTime dt = new DateTime();

		WeeklyStatistics ws = load(dt.getWeekOfWeekyear(), dt.getYear(), user
				.getCurrentProfile());

		ws.setTaskTaken(ws.getTaskTaken() +1);
		_session.merge(ws);
		_session.flush();
	}

	public void finishTask(TaskItem taskItem, User user) {
		DateTime dt = new DateTime();

		WeeklyStatistics ws = load(dt.getWeekOfWeekyear(), dt.getYear(), user
				.getCurrentProfile());

		ws.setTaskFinished(ws.getTaskFinished() +1);
		ws.setPoint(ws.getPoint() + taskItem.getPoint());

		UserProfile up = user.getCurrentProfile();
		up.setTotalPoint(up.getTotalPoint() + taskItem.getPoint());
		
		_session.merge(up);
		_session.merge(ws);
		_session.flush();
	}
}
