package com.cie2.tip.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.TaskItem.TaskStatus;

public class TaskActionService {

	static Logger logger = 
		Logger.getLogger(TaskActionService.class.getName());
	
	private Session _session;
		
	@Inject 
	TaskService taskService;
	
	public TaskActionService(Session session) {
		_session = session;
	}

	public TaskAction load(Long id) {
		return (TaskAction) _session.load(TaskAction.class, id);
	}
	
	public void addTaskAction(TaskAction taskAction, User user, TaskItem taskItem) {
		
		taskAction.setCreatedDate(new Date());
		taskAction.setTaskItem(taskItem);
		taskAction.setUser(user);
		
		_session.persist(taskAction);
	}

	public List getTaskActions(Long taskItemId) {
		
		List taskList = _session.createQuery(
				"from TaskAction ta where ta.taskItem.id=?").setParameter(0,
				taskItemId).list();
				
		return taskList;
		
	}
}
