package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;


public class ListTask extends CieUserPage {

	static Logger logger =
	       Logger.getLogger(ListTask.class.getName());
	
	@Property
	private TaskItem taskItem;
	
	@Inject
	private Session session;
	
	@Inject
	private RequestGlobals _requestGlobals;	

	
	public List<TaskItem> getTaskItems() {
		org.apache.tapestry5.services.Session sessionTap = _requestGlobals.getRequest().getSession(false);

//		logger.info("Visit=" + sessionTap.getAttribute("aso:com.cie2.tip.Visit"));
		
		return session.createCriteria(TaskItem.class).list();
	}

	 void onActionFromDelete(Long id) {
		 logger.info("Deleting Contact, With id : "  + id);
		 Object contact = session.load(TaskItem.class, id);
		 session.delete(contact);
		 session.getTransaction().commit();		 
     }
	 	
	public TaskItem getTaskItem() {
		return taskItem;
	}

	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;		
	}

}
