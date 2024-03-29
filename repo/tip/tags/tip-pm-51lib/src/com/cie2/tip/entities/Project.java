package com.cie2.tip.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Project implements Serializable{

	private Long id;
	private String name;
	// perlu banyak version gak ? 
	private String versions;
	private Boolean defaultProject;
	private Integer quorum;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual		
	public Long getId() {
		return id;
	}

	@Validate("required")
	public String getName() {
		return name;
	}

	public String getVersions() {
		return versions;
	}

	public Boolean getDefaultProject() {
		return defaultProject;
	}

	public Integer getQuorum() {
		return quorum;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public void setDefaultProject(Boolean defaultProject) {
		this.defaultProject = defaultProject;
	}

	public void setQuorum(Integer quorum) {
		this.quorum = quorum;
	}
}
