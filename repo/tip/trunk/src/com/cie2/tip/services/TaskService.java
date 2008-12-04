package com.cie2.tip.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserTask;
import com.cie2.tip.entities.TaskItem.TaskStatus;
import com.cie2.tip.entities.TaskItem.TaskType;

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
    	taskItem.setCreatedBy(currentUser);
    	System.out.println("== Current Project " + currentUser.getCurrentProject().getName());
    	taskItem.setProject(currentUser.getCurrentProject());
    	taskItem.setTaskStatus(TaskStatus.Created);
		taskItem.setTaskType(TaskType.Voted);
		calculatePoint(taskItem);
		
		// for all user add task to the user task entity		
		List allUser = _projectService.getAllUser(currentUser.getCurrentProject());
		
		UserTask userTask;
		
		_session.persist(taskItem);
		
		// add the new task for each user
		for (Iterator iter = allUser.iterator(); iter.hasNext();) {
			User user = (User) iter.next();
			userTask = new UserTask();
			userTask.setUser(user);
			userTask.setTask(taskItem);
			userTask.setVoted(false);
			
			_session.persist(userTask);
		}
		
		_session.flush();
		
	}
	
	private void calculatePoint(TaskItem taskItem) {
		if(TaskType.Voted.equals(taskItem.getTaskType()))
				taskItem.setPoint(3);
		else if (TaskType.Bonus.equals(taskItem.getTaskType()))
			taskItem.setPoint(5);
	}
	
	// semua list ini blom di check projectnya 
	// gimana cara supaya projectnya gak perlu di passing ? 
	// tapi udah default dari usernya ? 
	@SuppressWarnings("unchecked")
	public List <TaskItem> getUnvotedUserTask(User currentUser) {
		return _session
				.createQuery(
						"select task from UserTask ut inner join ut.task as task where ut.user=? and ut.voted=?")
				.setParameter(0, currentUser)
				.setParameter(1, false)
				.list();
	}	
	
	@SuppressWarnings("unchecked")
	public List <TaskItem> getVotedUserTask(User currentUser) {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Available))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<TaskItem> getWorkedOnTask(User user) {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Started))
				.add(Restrictions.eq("workBy", user))
				.list();
	}
	
	public void takeTask(Long id, User user) {
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, id);
		
		taskItem.setTaskStatus(TaskStatus.Started);
		taskItem.setStartDate(new Date());
		taskItem.setWorkBy(user);
		
		_session.update(taskItem);
		_session.flush();
	}
}
