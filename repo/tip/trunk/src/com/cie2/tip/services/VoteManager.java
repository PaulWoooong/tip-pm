package com.cie2.tip.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserTask;

public class VoteManager {
	static Logger logger = 
		Logger.getLogger(TaskService.class.getName());
	
	private Session _session;
		
	public VoteManager(Session session) {
		_session = session;
	}
	public void addVote(TaskItem taskItem, User user) {
		taskItem.setVote(taskItem.getVote() + 1);
	}
	
	public void castVote(Long id, User user) {
		System.out.println(" ======== Casting the vote for id " + id);
		_session.createQuery("update UserTask ut set ut.voted=? where ut.task.id=? and ut.user=?")
//		_session.createQuery("update UserTask ut set ut.voted=?")
			.setParameter(0, true)
			.setParameter(1, id)
			.setParameter(2, user)
			.executeUpdate();
		_session.flush();
//		UserTask ut = (UserTask) _session.createCriteria(UserTask.class)
//			.add(Restrictions.eq("task.id", id))
//			.add(Restrictions.eq("user", user)).uniqueResult();
//	
//		System.out.println(" UT Title " + ut.getTask().getTitle());
//		ut.setVoted(true);
//		_session.persist(ut);
	}
}
