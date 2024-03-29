package com.cie2.tip.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.grid.GridDataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.TaskItemDatasource;
import com.cie2.tip.entities.User;
import com.cie2.tip.entities.UserTask;
import com.cie2.tip.entities.TaskItem.TaskStatus;
import com.cie2.tip.entities.TaskItem.TaskType;
import com.cie2.tip.services.actions.FinishTaskAction;
import com.cie2.tip.services.actions.StartTaskAction;

public class TaskService {
	static Logger logger = Logger.getLogger(TaskService.class.getName());

	private Session _session;

	// services
	private ProjectService _projectService;
//
	private TaskActionService _taskActionService;

	private StatisticsService _statisticsService;
	
	public TaskService(Session session, ProjectService projectService,
			TaskActionService taskActionService, StatisticsService statisticsService) {
		_session = session;
		_projectService = projectService;
		_taskActionService = taskActionService;
		_statisticsService = statisticsService;
	}

	public void addTask(TaskItem taskItem, User currentUser) {
		// create task
		Date today = new Date();
		taskItem.setCreatedDate(today);
		taskItem.setLastChangedDate(today);
		taskItem.setCreatedBy(currentUser);
		taskItem.setProject(currentUser.getCurrentProject());
		taskItem.setTaskStatus(TaskStatus.Created);
		taskItem.setTaskType(TaskType.Voted);
		calculatePoint(taskItem);

		// for all user add task to the user task entity
		List allUser = _projectService.getAllUser(currentUser
				.getCurrentProject());

		UserTask userTask;

		_session.persist(taskItem);

		// add the new task for each user
		for (Iterator iter = allUser.iterator(); iter.hasNext();) {
			User user = (User) iter.next();
			userTask = new UserTask();
			userTask.setUser(user);
			userTask.setTask(taskItem);
			userTask.setVoted(false);

			_session.persist(userTask);
		}

		_statisticsService.createTask(taskItem, currentUser);
		_session.flush();

	}

	private void calculatePoint(TaskItem taskItem) {
		if (TaskType.Voted.equals(taskItem.getTaskType()))
			taskItem.setPoint(3);
		else if (TaskType.Bonus.equals(taskItem.getTaskType()))
			taskItem.setPoint(5);
	}

	public TaskItem load(Long id) {
		return (TaskItem) _session.load(TaskItem.class, id);
	}

	// semua list ini blom di check projectnya
	// gimana cara supaya projectnya gak perlu di passing ?
	// tapi udah default dari usernya ?
	@SuppressWarnings("unchecked")
	public List<TaskItem> getUnvotedTask(User currentUser) {
		Query query = _session
				.createQuery(
						"select task from UserTask ut inner join ut.task as task where ut.user=?"
								+ " and ut.voted=? and (task.taskStatus=? or task.taskStatus=?)")
				.setParameter(0, currentUser).setParameter(1, false)
				.setParameter(2, TaskStatus.Created).setParameter(3,
						TaskStatus.Available);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TaskItem> getInVoteTask() {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Created)).list();
	}

	@SuppressWarnings("unchecked")
	public List<TaskItem> getVotedTask() {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Available)).list();
	}

	@SuppressWarnings("unchecked")
	public List<TaskItem> getWorkedOnTask(User user) {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Started)).add(
				Restrictions.eq("workBy", user)).list();
	}


	public GridDataSource getWorkedOnTaskInGrid(User user) {
		Query query = _session.createQuery(
				"select ti from TaskItem as ti where ti.workBy=?")
		.setParameter(0, user).setParameter(1, false);
		TaskItemDatasource tid = new TaskItemDatasource(query);
		return tid;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskItem> getWorkedOnTask() {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Started)).list();
	}

	@SuppressWarnings("unchecked")
	public List<TaskItem> getFinishedTask() {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Finished)).list();
	}

//	public void getFinishedTaskGrid() {
//		HibernateGridDataSource ds = new HibernateGridDataSource(_session,TaskItem.class);
//	}
	
	
//	public GridDataSource getUnvoDataSource(User currentUser) {
//		HibernateGridDataSource datasource = new HibernateGridDataSource(_session, UserTask.class);
//		datasource.prepare(arg0, arg1, arg2)
//		return (GridDataSource) datasource;
//	}
	
	@SuppressWarnings("unchecked")
	public List<TaskItem> getFinishedTask(User user) {
		return _session.createCriteria(TaskItem.class).add(
				Restrictions.eq("taskStatus", TaskStatus.Finished)).add(
				Restrictions.eq("workBy", user)).list();
	}

	
	public void takeTask(Long id, User user) {
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, id);
		Date today = new Date();
		taskItem.setTaskStatus(TaskStatus.Started);
		taskItem.setStartDate(today);
		taskItem.setLastChangedDate(today);
		taskItem.setWorkBy(user);

		_session.update(taskItem);

		StartTaskAction taskAction = new StartTaskAction(user, taskItem);
		_taskActionService.addTaskAction(taskAction);
		_statisticsService.takeTask(taskItem, user);
		_session.flush();
	}

	public void finishTask(Long id, User user) {
		_session.flush();
		TaskItem taskItem = (TaskItem) _session.get(TaskItem.class, id);

		taskItem.setTaskStatus(TaskStatus.Finished);
		taskItem.setLastChangedDate(new Date());
		_session.update(taskItem);

		FinishTaskAction taskAction = new FinishTaskAction(user, taskItem);
		_taskActionService.addTaskAction(taskAction);
		_statisticsService.finishTask(taskItem, user);
		
		_session.flush();
	}
	/**/
}
