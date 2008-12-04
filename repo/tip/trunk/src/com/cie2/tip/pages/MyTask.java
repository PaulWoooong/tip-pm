package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.RequestGlobals;

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
	private RequestGlobals _requestGlobals;	

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
	
    public BeanModel getModel() {
        BeanModel model = beanModelSource.create(TaskItem.class, false, messages);
//        model.add("takeTask", null);
        return model;
    }	

	public TaskItem getTaskItem() {
		return taskItem;
	}

	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;		
	}
}
