package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Project;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.ProjectService;

public class ProjectList extends CieUserPage {

	@Property
	private Project project;
	
	// services
	@Inject
	private ProjectService projectService;
	
	public List<TaskItem> getProjects() {
		return projectService.getAllProject();
	}

}
