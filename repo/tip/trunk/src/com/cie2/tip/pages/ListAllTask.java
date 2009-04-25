package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;

public class ListAllTask extends CieUserPage {

	static Logger logger =
	       Logger.getLogger(ListAllTask.class.getName());
	
	@Property
	private TaskItem taskItem;
	
	@Inject
    private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;
	//services
	
	@Inject
	private TaskService taskService;

	public List<TaskItem> getMyTask() {
		User currentUser = getVisit().getUser();
		return taskService.getWorkedOnTask(currentUser);
	}
	
	public List<TaskItem> getNewTask() {
		User user = getVisit().getUser();
		return taskService.getUnvotedTask(user);
	}

	public List<TaskItem> getAvailableTask() {
		return taskService.getVotedTask();
	}
	
	public List<TaskItem> getInVoteTask() {
		return taskService.getInVoteTask();
	}

	public List<TaskItem> getFinishedTask() {
		return taskService.getFinishedTask();			
	}
	
	public List<TaskItem> getWorkedOnTask() {
		return taskService.getWorkedOnTask();
	}
	
		
	
	
    public BeanModel getMyTaskModel() {
        BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, messages);
        model.add("addAction", null);
        model.add("finish", null);        
        model.add("category", null);
        return model;
    }
    
	public BeanModel getNewTaskModel() {
		BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, 
				messages);
		model.add("castVote", null);
		model.add("category", null);
		return model;
	}
	
    public BeanModel getAvailableTaskModel() {
        BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, messages);
        model.add("takeTask", null);
        model.add("category", null);        
        return model;
    }	
    
    public BeanModel getInVoteModel() {
        BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, messages);
        model.add("category", null);
        return model;
    }	
    
	public BeanModel getOtherModel() {
		BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, 
				messages);
		model.add("user", null);
		model.add("category", null);
		return model;
	}
}
