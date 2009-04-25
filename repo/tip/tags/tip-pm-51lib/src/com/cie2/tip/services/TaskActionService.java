package com.cie2.tip.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskComment;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.actions.ITaskAction;

public class TaskActionService {

	static Logger logger = 
		Logger.getLogger(TaskActionService.class.getName());
	
	private Session _session;
		
	public TaskActionService(Session session) {
		_session = session;
	}

	public TaskAction load(Long id) {
		return (TaskAction) _session.load(TaskAction.class, id);
	}
	
	public TaskComment loadComment(Long id) {
		return (TaskComment) _session.load(TaskComment.class, id);
	}

	public void addTaskAction(TaskAction taskAction, User user, TaskItem taskItem) {
		
		taskAction.setCreatedDate(new Date());
		taskAction.setTaskItem(taskItem);
		taskAction.setUser(user);
		
		_session.persist(taskAction);
	}
	
	public void addTaskAction(ITaskAction taskAction) {
		TaskAction entityTaskAction = new TaskAction();

		entityTaskAction.setAction(taskAction.getAction());
		entityTaskAction.setCreatedDate(new Date());
		entityTaskAction.setTaskItem(taskAction.getTaskItem());
		entityTaskAction.setUser(taskAction.getUser());
		
		_session.persist(entityTaskAction);
	}

	public List getTaskActions(Long taskItemId) {
		
		List taskList = _session.createQuery(
				"from TaskAction ta where ta.taskItem.id=?").setParameter(0,
				taskItemId).list();
				
		return taskList;
		
	}

	public void addTaskComment(TaskComment taskComment, User user, TaskAction taskAction) {
		
		taskComment.setCreatedDate(new Date());
		taskComment.setTaskAction(taskAction);
		taskComment.setUser(user);
		_session.persist(taskComment);	
	}
	
	public List getTaskComment(Long taskActionId) {
		return _session.createCriteria(TaskComment.class).add(
				Restrictions.eq("taskAction.id", taskActionId)).list();		
	}
}
