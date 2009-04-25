package com.cie2.tip.services.actions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;

public interface ITaskAction {
	public String getAction();
	
	public User getUser();
	
	public TaskItem getTaskItem();
}
