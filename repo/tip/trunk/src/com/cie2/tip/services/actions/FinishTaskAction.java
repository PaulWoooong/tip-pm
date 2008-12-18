package com.cie2.tip.services.actions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;

public class FinishTaskAction extends AbstractTaskAction {

	
	public FinishTaskAction(User user, TaskItem taskItem) {
		super(user, taskItem);
	}

	public String getAction() {
		return "Task Finished by " + _user.getUsername();
	}
}
