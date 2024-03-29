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
public class WeeklyStatistics {

	private Long id;
	
	private Date date;
	
	private Integer week;

	private Integer year;
	
	private UserProfile userProfile;
	
	private Integer point;
	
	private Integer taskTaken;
	
	private Integer taskFinished;
		
	private Integer taskCreated;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual			
	public Long getId() {
		return id;
	}

	public Integer getPoint() {
		return point;
	}

	public Integer getTaskCreated() {
		return taskCreated;
	}

	public Integer getTaskFinished() {
		return taskFinished;
	}


	public Integer getTaskTaken() {
		return taskTaken;
	}

    @ManyToOne(targetEntity=UserProfile.class)
    @JoinColumn(name="USER_PROFILE_ID")	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public Integer getWeek() {
		return week;
	}

	public Integer getYear() {
		return year;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public void setTaskCreated(Integer taskCreated) {
		this.taskCreated = taskCreated;
	}

	public void setTaskFinished(Integer taskFinished) {
		this.taskFinished = taskFinished;
	}

	public void setTaskTaken(Integer taskTaken) {
		this.taskTaken = taskTaken;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
}
