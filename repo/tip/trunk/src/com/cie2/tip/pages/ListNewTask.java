package com.cie2.tip.pages;

import java.util.List;
import java.util.logging.Logger;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.TaskService;
import com.cie2.tip.services.VoteManager;

public class ListNewTask extends CieUserPage {

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

	@Inject
	private VoteManager voteManager;

	public List<TaskItem> getTaskItems() {
		User user = getVisit().getUser();
		return taskService.getUnvotedTask(user);
	}

	@CommitAfter
	void onActionFromVoteUp(Long id) {
		logger.info("Vote Up");
		User user = getVisit().getUser();
		voteManager.voteUp(id, user);

	}

	@CommitAfter
	void onActionFromVoteDown(Long id) {
		logger.info("Vote Down !!");
		User user = getVisit().getUser();
		voteManager.voteDown(id, user);

	}
	
	public BeanModel getModel() {
		BeanModel model = beanModelSource.create(TaskItem.class, false,
				messages);
		model.add("castVote", null);
		model.add("category", null);
		return model;
	}

	public TaskItem getTaskItem() {
		return taskItem;
	}

	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;
	}

}
