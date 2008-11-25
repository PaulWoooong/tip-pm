package com.cie2.tip.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class TaskItem implements Serializable{

	public enum TaskType {
		Assigned, Proposed, Bonus
	}
	
	public enum TaskStatus {
		Created, Available, Assigned,  Finished
	}
	
    private Long id;
    
    private User user;
    
    private String title;
    private String description;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private int vote;
    private int point;    
    private Date createdDate;
    private Date startDate;
    private Date lastChangedDate;
    private Project project;
    private Category category;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual    
	public Long getId() {
		return id;
	}

	
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}
    
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

    public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public TaskType getTaskType() {
		return taskType;
	}

	public int getVote() {
		return vote;
	}
	
	public int getPoint() {
		return point;
	}
	
	@NonVisual
	public Date getCreatedDate() {
		return createdDate;
	}

	@NonVisual
	public Date getLastChangedDate() {
		return lastChangedDate;
	}
	
	@NonVisual
	public Date getStartDate() {
		return startDate;
	}
	
    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
	public Project getProject() {
		return project;
	}
	
    @ManyToOne(targetEntity=Category.class)
    @JoinColumn(name="CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}

	public void setProject(Project project) {
		this.project = project;
	}



	public void setCategory(Category category) {
		this.category = category;
	}


}
