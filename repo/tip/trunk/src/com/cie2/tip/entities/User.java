package com.cie2.tip.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class User implements Serializable{

	public enum AccessLevel {
		Admin, User,  DataEntry
	}
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private Boolean defaultPass;
	
	private Integer profileId;
	
	private AccessLevel accessLevel;

	private Boolean ableToVote;
	
	private Project currentProject;
	
	private Date LastLogin;
	
	// kalau udah multiple project bakal gak kepake
	private Integer point;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual		
	public Long getId() {
		return id;
	}

	@Validate("required")
	public String getUsername() {
		return username;
	}

	@Validate("required")
	public String getPassword() {
		return password;
	}

	@NonVisual
	public Integer getProfileId() {
		return profileId;
	}
	
	@NonVisual
	public Boolean getDefaultPass() {
		return defaultPass;
	}

	public Boolean getAbleToVote() {
		return ableToVote;
	}

	
    @OneToOne
    @JoinColumn(name="CURRENT_PROJECT_ID")
	public Project getCurrentProject() {
		return currentProject;
	}
	
	public Date getLastLogin() {
		return LastLogin;
	}

	public Integer getPoint() {
		return point;
	}

	public void setDefaultPass(Boolean defaultPass) {
		this.defaultPass = defaultPass;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Validate("required")
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void setAbleToVote(Boolean ableToVote) {
		this.ableToVote = ableToVote;
	}


	public void setCurrentProject(Project defaultProject) {
		this.currentProject = defaultProject;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}


	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

}
