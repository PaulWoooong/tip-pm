package com.cie2.tip.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.TaskService;

public class TaskItemDetail extends CieUserPage {

	@Property
	private TaskItem taskItem;

	private Long id;

	//services
	@Inject
	private TaskService taskService;

	void onActivate(Long id) {
		this.id = id;
		taskItem = (TaskItem) taskService.load(id);
	}

	Long onPassivate() {
		return id;
	}
}
