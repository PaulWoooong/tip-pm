package com.cie2.tip.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Category;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;


public class CreateTask extends CieUserPage{

    private Long id;

	@Property
	private TaskItem taskItem;

	private Category category;

    @Inject
    private Session _session;
    
    
    // services
    
    @Inject
    private TaskService taskService;
    
    @CommitAfter
    Object onSuccess()
    {    	
    	User user = getVisit().getUser();
    	taskService.addTask(taskItem, user);

        return ListNewTask.class;
    }

    void onActivate(Long id) {
    	this.id = id;
    	taskItem = (TaskItem) _session.load(TaskItem.class, id);    	
     }

     Long onPassivate() {
        return id;
     }    
}
