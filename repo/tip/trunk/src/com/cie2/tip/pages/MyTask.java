package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;

public class MyTask extends CieUserPage {

	static Logger logger = Logger.getLogger(MyTask.class.getName());

	@Property
	private TaskItem taskItem;

	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private ComponentResources componentResources;

	@SuppressWarnings("unchecked")
	@Property
	@Retain
	private BeanModel model;

	//services
	@Inject
	private TaskService taskService;

	public List<TaskItem> getTaskItems() {
		User currentUser = getVisit().getUser();
		return taskService.getWorkedOnTask(currentUser);
	}

	// di set ke user, tapi kalo udah multiple project. Tiap project milik user
	// harus punya sendiri
	@CommitAfter
	public void onActionFromFinish(Long id) {
		User user = getVisit().getUser();
		taskService.finishTask(id, user);
	}

	void setupRender() {

		if (model == null) {
			model = beanModelSource.createDisplayModel(TaskItem.class,
					componentResources.getMessages());
			model.add("addAction", null);
			model.add("finish", null);
			model.add("category", null);
		}

		//		// Get all persons - ask business service to find them (from the database)
		//		_persons = getPersonService().findPersons();
	}

}
