package com.cie2.tip.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskActionService;
import com.cie2.tip.services.TaskService;

public class CreateTaskAction extends CieUserPage {

	private Long taskItemId;
	
	@Property
	private TaskAction taskAction;
	
	@InjectPage
	private TaskItemDetail taskItemDetail;
	
	//services
	@Inject
	private TaskActionService taskActionService;
	
	@Inject
	private TaskService taskService;
	
	void onActivate(Long taskItemId) {
		this.taskItemId = taskItemId;
//		taskAction = taskActionService.load(taskItemId);
	}
	
	Long onPassivate() {
		return this.taskItemId;
	}
	
	@CommitAfter
	Object onSuccess() {
		User user = getVisit().getUser();
		TaskItem taskItem = taskService.load(taskItemId);
		taskActionService.addTaskAction(taskAction, user, taskItem);
		
		taskItemDetail.onActivate(taskItem.getId());
		return taskItemDetail;
	}
	
}
