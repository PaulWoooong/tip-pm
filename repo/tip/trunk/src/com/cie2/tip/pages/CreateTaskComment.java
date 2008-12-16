package com.cie2.tip.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskComment;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskActionService;

public class CreateTaskComment extends CieUserPage {

	private Long taskActionId;
	
	@Property
	private TaskComment taskComment;
	
	@InjectPage
	private TaskItemDetail taskItemDetail;
	
	//services
	@Inject
	private TaskActionService taskActionService;
		
	void onActivate(Long taskActionId) {
		this.taskActionId = taskActionId;
//		taskAction = taskActionService.load(taskItemId);
	}
	
	Long onPassivate() {
		return this.taskActionId;
	}
	
	@CommitAfter
	Object onSuccess() {
		User user = getVisit().getUser();
		TaskAction taskAction = taskActionService.load(taskActionId);
		taskActionService.addTaskComment(taskComment, user, taskAction);
		
		taskItemDetail.onActivate(taskAction.getTaskItem().getId());
		return taskItemDetail;
	}
}
