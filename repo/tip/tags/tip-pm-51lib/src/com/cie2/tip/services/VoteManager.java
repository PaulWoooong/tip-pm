package com.cie2.tip.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
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
	
	public void voteUp(Long taskId, User user) {
		logger.info(" ======== Casting the vote for id " + taskId);
		_session.createQuery("update UserTask ut set ut.voted=? where ut.task.id=? and ut.user=?")
			.setParameter(0, true)
			.setParameter(1, taskId)
			.setParameter(2, user)
			.executeUpdate();
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, taskId);
		taskItem.setVoteUp(taskItem.getVoteUp() + 1);
		calculateVote(taskItem, user);
		_session.flush();
	}

	public void voteDown(Long taskId, User user) {
		logger.info(" Voting down Task Item " + taskId);
		_session.createQuery("update UserTask ut set ut.voted=? where ut.task.id=? and ut.user=?")
			.setParameter(0, true)
			.setParameter(1, taskId)
			.setParameter(2, user)
			.executeUpdate();
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, taskId);
		taskItem.setVoteDown(taskItem.getVoteDown() + 1);
		calculateVote(taskItem, user);
		_session.flush();
	}

	private void calculateVote(TaskItem taskItem, User user) {
		if(taskItem.getVoteUp() >= _projectService.getProject(user).getQuorum() &&
				!taskItem.getTaskStatus().equals(TaskStatus.Available)) {
			taskItem.setTaskStatus(TaskStatus.Available);
		}
	}
}
