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
	
	private UserProfile currentProfile;
	
	private Project currentProject;
	
	private Date LastLogin;
	
	
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
	@Validate("required")
    @OneToOne
    @JoinColumn(name="CURRENT_PROFILE_ID")
	public UserProfile getCurrentProfile() {
		return currentProfile;
	}
	
	@NonVisual
	public Boolean getDefaultPass() {
		return defaultPass;
	}
	
    @OneToOne
    @JoinColumn(name="CURRENT_PROJECT_ID")
	public Project getCurrentProject() {
		return currentProject;
	}
	
	public Date getLastLogin() {
		return LastLogin;
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

	public void setProfileId(UserProfile userProfile) {
		this.currentProfile = userProfile;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCurrentProject(Project defaultProject) {
		this.currentProject = defaultProject;
	}


	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

	public void setCurrentProfile(UserProfile currentProfile) {
		this.currentProfile = currentProfile;
	}

}
