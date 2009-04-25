package com.cie2.tip.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.tapestry5.beaneditor.NonVisual;

/**
 * @ToDO implement action for every state of the program
 * @author abangkis
 *
 */
@Entity
public class TaskAction {


	private Long id;
	
	private TaskItem taskItem;
	
	private String action;
	
	private Date createdDate;
	
	private User user;

	private List <TaskComment> taskComments;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual  
	public Long getId() {
		return id;
	}

	public String getAction() {
		return action;
	}
	
	
	@ManyToOne(targetEntity=TaskItem.class)
    @JoinColumn(name="TASKITEM_ID")	
	public TaskItem getTaskItem() {
		return taskItem;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@ManyToOne(targetEntity=User.class)
    @JoinColumn(name="USER")
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

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@OneToMany(mappedBy="taskAction")
	public List<TaskComment> getTaskComments() {
		return taskComments;
	}

	public void setTaskComments(List<TaskComment> taskComments) {
		this.taskComments = taskComments;
	}
	
}
