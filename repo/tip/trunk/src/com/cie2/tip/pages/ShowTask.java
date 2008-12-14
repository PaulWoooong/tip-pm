package com.cie2.tip.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.TaskItem.TaskStatusLimited;
import com.cie2.tip.services.TaskService;

public class ShowTask extends CieUserPage {
	
	static Logger logger = Logger.getLogger(ShowTask.class.getName());
		
    @Persist
    private TaskStatusLimited _status;
	
	@Property
	private TaskItem taskItem;

	@Persist
	private List listOfTaskItem;
	
	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;

	// services
	@Inject
	private TaskService taskService;

	
	public List<TaskItem> getTaskItems() {

		if(null == listOfTaskItem)
			listOfTaskItem = new ArrayList();
		
		return listOfTaskItem;		
	}

	public BeanModel getModel() {
		BeanModel model = beanModelSource.create(TaskItem.class, false,
				messages);
//		model.add("castVote", null);
		return model;
	}
	

	public Object onSuccess() {
		if(TaskStatusLimited.Finished.equals(getTaskStatus())) {
			listOfTaskItem = taskService.getFinishedTask();			
		}
		else  if(TaskStatusLimited.Started.equals(getTaskStatus())) {
			listOfTaskItem = taskService.getWorkedOnTask();
		}
		
		return ShowTask.class;
	}
	
	
    @Validate("required")
    public TaskStatusLimited getTaskStatus() { return _status; }

    public void setTaskStatus(TaskStatusLimited status) { _status = status; }

	public TaskItem getTaskItem() {
		return taskItem;
	}

	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;
	}

	
}
