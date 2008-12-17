package com.cie2.tip.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class TaskComment {

	private Long id;
	
	private TaskAction taskAction;
	
	private String comment;
	
	private Date createdDate;
	
	private User user;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual  	
	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@ManyToOne
	public TaskAction getTaskAction() {
		return taskAction;
	}

	@ManyToOne(targetEntity=User.class)
    @JoinColumn(name="USER")	
	public User getUser() {
		return user;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaskAction(TaskAction taskAction) {
		this.taskAction = taskAction;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
