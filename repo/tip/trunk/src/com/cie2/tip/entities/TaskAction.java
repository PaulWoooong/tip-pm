package com.cie2.tip.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;

/**
 * @ToDO implement action for every state of the program
 * @author abangkis
 *
 */
public class TaskAction {


	private Long id;
	
	private TaskItem taskItem;
	
	private String action;
	
	private User user;

	public String getAction() {
		return action;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual  
	public Long getId() {
		return id;
	}

	public TaskItem getTaskItem() {
		return taskItem;
	}


	public User getUser() {
		return user;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
}
