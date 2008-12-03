package com.cie2.tip.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserTask;
import com.cie2.tip.entities.TaskItem.TaskStatus;

public class VoteManager {
	static Logger logger = 
		Logger.getLogger(TaskService.class.getName());
	
	private Session _session;

	private ProjectService _projectService;
		
	public VoteManager(Session session, ProjectService projectService) {
		_session = session;
		_projectService = projectService;
	}
	
	public void castVote(Long taskId, User user) {
		System.out.println(" ======== Casting the vote for id " + taskId);
		_session.createQuery("update UserTask ut set ut.voted=? where ut.task.id=? and ut.user=?")
			.setParameter(0, true)
			.setParameter(1, taskId)
			.setParameter(2, user)
			.executeUpdate();
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, taskId);
		taskItem.setVote(taskItem.getVote() + 1);
		calculateVote(taskItem, user);
		_session.flush();
	}
	
	private void calculateVote(TaskItem taskItem, User user) {
		if(taskItem.getVote() >= _projectService.getProject(user).getQuorum() &&
				!taskItem.getTaskStatus().equals(TaskStatus.Available)) {
			taskItem.setTaskStatus(TaskStatus.Available);
		}
	}
}
