package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.RequestGlobals;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;


public class ListTask extends CieUserPage {

	static Logger logger =
	       Logger.getLogger(ListTask.class.getName());
	
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
//		org.apache.tapestry5.services.Session sessionTap = _requestGlobals.getRequest().getSession(false);

//		logger.info("Visit=" + sessionTap.getAttribute("aso:com.cie2.tip.Visit"));

		return taskService.getVotedTask();
	}
	
    @CommitAfter
    void onActionFromTakeTask(Long id) {
		User user = getVisit().getUser();
    	taskService.takeTask(id, user);
    }
    
//	 void onActionFromDelete(Long id) {
//		 logger.info("Deleting Contact, With id : "  + id);
//		 Object contact = session.load(TaskItem.class, id);
//		 session.delete(contact);
//		 session.getTransaction().commit();		 
//     }

    
    public BeanModel getModel() {
        BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, messages);
        model.add("takeTask", null);
        model.add("category", null);        
        return model;
    }	

}
