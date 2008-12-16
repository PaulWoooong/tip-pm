package com.cie2.tip.pages;

import java.util.Iterator;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskAction;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.TaskActionService;
import com.cie2.tip.services.TaskService;

public class TaskItemDetail extends CieUserPage {

	@Property
	private TaskItem taskItem;

	@Property
	private TaskAction taskAction;
	
	private Long id;

	//services
	@Inject
	private TaskService taskService;

	@Inject
	private TaskActionService taskActionService;
	
	
	void onActivate(Long taskItemId) {
		this.id = taskItemId;
		taskItem = (TaskItem) taskService.load(taskItemId);
	}

	Long onPassivate() {
		return id;
	}
	
	public List getTaskActions() {
		
		List taskActions = taskActionService.getTaskActions(id);
		
			return taskActions;
//		System.out.println("=== Getting task Actions ");
//		List taskActions = taskActionService.getTaskActions(id);
//		
//		System.out.println("size " + taskActions.size());
//		for (int i = 0; i < taskActions.size(); i++) {
//			System.out.println("index " + i);
//			TaskAction ta = (TaskAction) taskActions.get(i);
//			System.out.println("I: "  + " " + ta.getAction());
//			
//		}
//
//		return taskActions;
	}
}
