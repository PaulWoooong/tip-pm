package com.cie2.tip.services.actions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;

public class StartTaskAction extends AbstractTaskAction{
	
	public StartTaskAction(User user, TaskItem taskItem) {
		super(user, taskItem);
	}

	public String getAction( ) {
		return "Task taken by " + _user.getUsername();
	}
	
}
