package com.cie2.tip.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.Project;
import com.cie2.tip.entities.User;

public class ProjectService {

	static Logger logger = 
		Logger.getLogger(ProjectService.class.getName());
	
	private Session _session;
	
	public ProjectService(Session session) {
		_session = session;
	}

	public void createProject(Project newProject) {
		if(newProject.getDefaultProject()) {
			Project oldDefault = getDefaultProject();
			oldDefault.setDefaultProject(false);
			_session.update(oldDefault);
		}
		
		_session.persist(newProject);
		_session.flush();
	}
	
	public Project getDefaultProject() {
		Project project = (Project) _session.createQuery(
				"from Project p where defaultProject=?").setParameter(0, true).uniqueResult();
		
		if(project == null)
			project = createDefaultProject();
		else
			logger.info("Find Default Project " + project.getName());
		
		return project;		
		
	}
	
	// gak butuh nih 
	public Project getProject(User user) {
		return (Project) _session.createCriteria(Project.class).add(
				Restrictions.eq("id", user.getCurrentProject().getId())).uniqueResult();		
	}
		
	private Project createDefaultProject() {
		logger.info("Creating Default Project");
		Project project = new Project();
		project.setName("Default Project");
		project.setVersions("0.1");
		project.setDefaultProject(true);
		_session.persist(project);
		
		return project;
	}
}
