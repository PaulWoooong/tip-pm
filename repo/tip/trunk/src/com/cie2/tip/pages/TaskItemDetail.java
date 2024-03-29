package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskComment;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.TaskActionService;
import com.cie2.tip.services.TaskService;

public class TaskItemDetail extends CieUserPage {

	@Property
	private TaskItem taskItem;

	@Property
	private TaskAction taskAction;
	
	@Property 
	private TaskComment taskComment;
	
	private Long taskItemId;

	//services
	@Inject
	private TaskService taskService;

	@Inject
	private TaskActionService taskActionService;
	
	
	void onActivate(Long taskItemId) {
		this.taskItemId = taskItemId;
		taskItem = (TaskItem) taskService.load(taskItemId);
	}

	Long onPassivate() {
		return taskItemId;
	}
	
	public List getTaskActions() {
		List taskActions = taskActionService.getTaskActions(taskItemId);		
			return taskActions;
	}
}
