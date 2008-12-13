package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;

public class MyTask extends CieUserPage {

	static Logger logger =
	       Logger.getLogger(MyTask.class.getName());
	
	@Property
	private TaskItem taskItem;
		
	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;

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
	
    public BeanModel getModel() {
        BeanModel model = beanModelSource.createEditModel(TaskItem.class, messages);
        model.add("finish", null);
        return model;
    }	
}
