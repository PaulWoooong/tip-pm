package com.cie2.tip.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Category implements Serializable{

	private Long id;
	
	private Category parentCategory;
	
	private String name;
	
	private String description;
	
	private Project project;
	

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

	public String getDescription() {
		return description;
	}
	
    @ManyToOne(targetEntity=Category.class)
    @JoinColumn(name="PARENT_ID")
	public Category getParentCategory() {
		return parentCategory;
	}

    @ManyToOne
    @JoinColumn(name="PROJECT_ID")    
	public Project getProject() {
		return project;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
