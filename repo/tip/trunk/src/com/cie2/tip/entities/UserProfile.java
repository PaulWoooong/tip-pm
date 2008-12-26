package com.cie2.tip.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import com.cie2.tip.entities.User.AccessLevel;

@Entity
public class UserProfile {

	private Long id;
	
	private Integer totalPoint;
	
	private User user;
	
	private Project project;
	
	private AccessLevel accessLevel;

	private Boolean ableToVote;	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual			
	public Long getId() {
		return id;
	}
	
	public Boolean getAbleToVote() {
		return ableToVote;
	}

	@Validate("required")
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	
    @ManyToOne(targetEntity=Project.class)
    @JoinColumn(name="PROJECT_ID")
	public Project getProject() {
		return project;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void setAbleToVote(Boolean ableToVote) {
		this.ableToVote = ableToVote;
	}
	
}
