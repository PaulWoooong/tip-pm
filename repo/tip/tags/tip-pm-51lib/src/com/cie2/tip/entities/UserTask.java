package com.cie2.tip.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class UserTask {

	private Long id;
	
	private User user;
	
	private TaskItem task;

	private Boolean voted;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual 
	public Long getId() {
		return id;
	}

    @ManyToOne(targetEntity=TaskItem.class)
    @JoinColumn(name="TASK_ID")
	public TaskItem getTask() {
		return task;
	}

    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public Boolean getVoted() {
		return voted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTask(TaskItem task) {
		this.task = task;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setVoted(Boolean voted) {
		this.voted = voted;
	}
}
