package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Category;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.models.EasyIdSelectModel;
import com.cie2.tip.services.CategoryService;
import com.cie2.tip.services.ProjectService;
import com.cie2.tip.services.TaskService;


public class CreateTask extends CieUserPage{

	static Logger logger =
	       Logger.getLogger(CreateTask.class.getName());

	private Long id;

	@Property
	private TaskItem taskItem;


	@SuppressWarnings("unused")
	@Property
	@Persist
	private EasyIdSelectModel<Category> myModel;

	@Property
	private Long _categoryId;

	private Category category;

	// services    
    @Inject
    private TaskService taskService;

    @Inject
	private CategoryService categoryService;

    @Inject
    private ProjectService projectServices;

	@Inject
	private PropertyAccess _propertyAccess;
    
    @CommitAfter
    Object onSuccess()
    {    	
    	User user = getVisit().getUser();
    	taskItem.getCategory();
    	taskService.addTask(taskItem, user);

        return ListNewTask.class;
    }

    // kenapa onactivate nya dua2nya gak dipanggil ? 
//    cuma begin render doang yang dipanggil ?
    void beginRender() {
    	if(null == myModel) {
    		logger.info("Creating my model");

    		List<Category> categories = categoryService.findCategory(projectServices.getDefaultProject());
    		logger.info("Refresh category size : " + categories.size());
    		myModel = new EasyIdSelectModel<Category>(categories, Category.class, "name", "id", _propertyAccess);    	
    	}
    	else
    		refreshCategory();
    }
    
    void onActivate(Long id) {
    	logger.info("=== Calling on active ID");
    	refreshCategory();
    	this.id = id;
    	taskItem = taskService.load(id);
     }

	void onActivate() {
		logger.info("=== Calling on active");
		refreshCategory();
	}

	void refreshCategory() {		
		List<Category> categories = categoryService.findCategory(projectServices.getDefaultProject());
		logger.info("Refresh category size : " + categories.size());
		myModel.setList(categories);
	}
	
     Long onPassivate() {
        return id;
     }    
}
