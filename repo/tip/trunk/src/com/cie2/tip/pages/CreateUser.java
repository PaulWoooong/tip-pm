package com.cie2.tip.pages;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.cie2.tip.entities.Project;
import com.cie2.tip.entities.User;
import com.cie2.tip.services.ProjectService;


public class CreateUser {

	static Logger logger =
	       Logger.getLogger(CreateUser.class.getName());
	
    private Long id;

	@Property
	private User user;
	
    @Inject
    private Session _session;

    @Inject
    private ProjectService projectServices;
    
    @CommitAfter
    Object onSuccess()
    {    	
    	Project currentProject = projectServices.getDefaultProject();
    	logger.info("Current Project = " + currentProject.getName());
    	user.setCurrentProject(currentProject);
        _session.persist(user);

        return CreateUser.class;
    }

    void onActivate(Long id) {
    	this.id = id;
    	user = (User) _session.load(User.class, id);    	
     }

     Long onPassivate() {
        return id;
     }    
    
}
