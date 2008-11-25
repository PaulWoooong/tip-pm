package com.cie2.tip.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Category;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.ProjectService;


public class CreateTask extends CieUserPage{

    private Long id;

	@Property
	private TaskItem taskItem;

	private Category category;

    @Inject
    private Session _session;
    
    
    @CommitAfter
    Object onSuccess()
    {    	
    	User user = getVisit().getUser();
    	Date today = new Date();
    	taskItem.setCreatedDate(today);
    	taskItem.setLastChangedDate(today);
    	taskItem.setUser(user);
    	System.out.println("== Current Project " + user.getCurrentProject());
    	taskItem.setProject(user.getCurrentProject());
        _session.persist(taskItem);

        return ListTask.class;
    }

    void onActivate(Long id) {
    	this.id = id;
    	taskItem = (TaskItem) _session.load(TaskItem.class, id);    	
     }

     Long onPassivate() {
        return id;
     }    
}
