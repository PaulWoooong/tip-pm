package com.cie2.tip.pages;

import java.util.List;
import java.util.logging.Logger;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.TaskService;

/**
 * We create this page, because we couldn't show this page in list other task page.
 * In this list, the workBy field is still null, while in the list other task page 
 * the task is shown categorized by the person who are working on that task.
 * @author abangkis
 *
 */
public class ListInVoteTask extends CieUserPage {

	static Logger logger = Logger.getLogger(ListNewTask.class.getName());

	@Property
	private TaskItem taskItem;

	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;

	// services
	@Inject
	private TaskService taskService;

	public List<TaskItem> getTaskItems() {
		return taskService.getInVoteTask();
	}

    public BeanModel getModel() {
        BeanModel model = beanModelSource.createDisplayModel(TaskItem.class, messages);
        model.add("category", null);
        return model;
    }		
}
