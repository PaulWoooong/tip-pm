package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.RequestGlobals;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;

public class ListNewTask extends CieUserPage{

	static Logger logger =
	       Logger.getLogger(ListNewTask.class.getName());
	
	
	@Property
	private TaskItem taskItem;
	
	@Inject
	private Session session;
	
	@Inject
	private RequestGlobals _requestGlobals;	

	@Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;

    // services
    @Inject
    private TaskService taskService;
    
    
	public List<TaskItem> getTaskItems() {
//		org.apache.tapestry5.services.Session sessionTap = _requestGlobals.getRequest().getSession(false);

//		logger.info("Visit=" + sessionTap.getAttribute("aso:com.cie2.tip.Visit"));
		User user = getVisit().getUser();
		return taskService.getUnvotedUserTask(user);
	}



    public BeanModel getModel() {
        BeanModel model = beanModelSource.create(TaskItem.class, false, messages);
        model.add("castVote", null);
        return model;
    }	
	

	 void onActionFromCastVote(Long id) {
		 logger.info("Cast a vote ");
		 User user = getVisit().getUser();
		 taskService.castVote(id, user);
		 
	 }



	public TaskItem getTaskItem() {
		return taskItem;
	}



	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;
	}




}
