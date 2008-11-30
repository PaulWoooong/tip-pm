package com.cie2.tip.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserTask;
import com.cie2.tip.entities.TaskItem.TaskStatus;

public class TaskService {
	static Logger logger = 
		Logger.getLogger(TaskService.class.getName());
	
	private Session _session;
	
	private ProjectService _projectService;
	
	public TaskService(Session session, ProjectService projectService) {
		_session = session;
		_projectService = projectService;
	}
	
	public void addTask(TaskItem taskItem, User currentUser) {
		// create task
    	Date today = new Date();
    	taskItem.setCreatedDate(today);
    	taskItem.setLastChangedDate(today);
    	taskItem.setUser(currentUser);
    	System.out.println("== Current Project " + currentUser.getCurrentProject().getName());
    	taskItem.setProject(currentUser.getCurrentProject());
    	taskItem.setTaskStatus(TaskStatus.Created);
		
		// for all user add task to the user task entity		
		List allUser = _projectService.getAllUser(currentUser.getCurrentProject());
		
		UserTask userTask;
		
		_session.persist(taskItem);
		
		for (Iterator iter = allUser.iterator(); iter.hasNext();) {
			User user = (User) iter.next();
			System.out.println("Creating user task for : " + user.getUsername());			
			userTask = new UserTask();
			userTask.setUser(user);
			userTask.setTask(taskItem);
			userTask.setVoted(false);
			
			_session.persist(userTask);
		}
		
		_session.flush();
		
	}
	
	@SuppressWarnings("unchecked")
	public List <TaskItem> getUnvotedUserTask(User currentUser) {
		return _session
				.createQuery(
						"select task from UserTask ut inner join ut.task as task where ut.user=? and ut.voted=?")
				.setParameter(0, currentUser)
				.setParameter(1, false)
				.list();
	}	
	
	public void castVote(Long id, User user) {
		System.out.println(" ======== Casting the vote for id " + id);
//		_session.createQuery("update UserTask ut set ut.voted=? where ut.task.id=? and ut.user=?")
		_session.createQuery("update UserTask ut set ut.voted=?")
			.setParameter(0, true)
//			.setParameter(1, id)
//			.setParameter(2, user)
			.executeUpdate();
		_session.flush();
	}
}
