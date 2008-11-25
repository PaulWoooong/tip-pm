package com.cie2.tip.pages;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Project;
import com.cie2.tip.entities.Project;
import com.cie2.tip.services.ProjectService;

public class CreateProject extends CieUserPage{

	static Logger logger =
	       Logger.getLogger(CreateProject.class.getName());
	
    private Long id;
    
	@Property
	private Project project;
	
    @Inject
    private Session _session;

    @Inject 
    private ProjectService projectServices;
    
    @CommitAfter
    Object onSuccess()
    {
    	projectServices.createProject(project);

        return MyProfile.class;
    }

    void onActivate(Long id) {
    	this.id = id;
    	project = (Project) _session.load(Project.class, id);    	
     }

     Long onPassivate() {
        return id;
     }    
    
}
