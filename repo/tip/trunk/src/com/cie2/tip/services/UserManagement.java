package com.cie2.tip.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.cie2.tip.entities.Project;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserProfile;

public class UserManagement {

	static Logger logger = 
		Logger.getLogger(TaskService.class.getName());
	
	private Session _session;

	private ProjectService _projectService;
		
	public UserManagement(Session session, ProjectService projectService) {
		_session = session;
		_projectService = projectService;
	}
	
	public void addUser(User user) {
	   	Project currentProject = _projectService.getDefaultProject();
    	logger.info("Current Project = " + currentProject.getName());
    	user.setCurrentProject(currentProject);
    	
    	UserProfile userProfile = new UserProfile();
    	userProfile.setProject(currentProject);
    	userProfile.setUser(user);
    	userProfile.setTotalPoint(0);
    	
    	user.setCurrentProfile(userProfile);

    	_session.persist(user);

	}
	
	private void populateUserTask() {
		//ambil task yang belum di vote. add to user task
	}
	
	@SuppressWarnings("unchecked")
	public List <User> getActiveUser() {
		return _session.createCriteria(User.class).list();
	}
}
