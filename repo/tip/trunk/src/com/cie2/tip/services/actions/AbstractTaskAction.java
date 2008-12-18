package com.cie2.tip.services.actions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;

public abstract class AbstractTaskAction implements ITaskAction {

	protected User _user;
	
	protected TaskItem _taskItem;
	
	public AbstractTaskAction(User user, TaskItem taskItem) {
		_user = user;
		_taskItem = taskItem;
	}

	public TaskItem getTaskItem() {
		return _taskItem;
	}

	public User getUser() {
		return _user;
	}}
