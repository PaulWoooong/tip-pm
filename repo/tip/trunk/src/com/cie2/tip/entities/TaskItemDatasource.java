package com.cie2.tip.entities;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.hibernate.Query;

public class TaskItemDatasource implements GridDataSource {

	private Query query;
	List <TaskItem> selection;
	int indexFrom;
	
	public TaskItemDatasource(Query query) {
		System.out.println("=== Constructing ");
		this.query = query;
	}
	
	public int getAvailableRows() {
		System.out.println("=== Getting rows ");
		return selection.size();
	}

	public Class getRowType() {
		System.out.println("=== Get Row Type ");		
		return TaskItem.class;
	}

	public Object getRowValue(int i) {
		
		System.out.println("Getting value for row " + i);
//		return selection.get(i);
		return selection.get(i - this.indexFrom);
	}

	public void prepare(int indexFrom, int indexTo, List<SortConstraint> propertyModel) {
		System.out.println("Preparing selection.");
		System.out.println("Index from " + indexFrom + " to " + indexTo);
		query.setMaxResults(2);
		query.setFirstResult(1);
		selection = query.list();
		this.indexFrom = indexFrom;
	}

//	public void Query createCountQuery() {
//		( (Integer) session.createQuery("select count(*) from ....").iterate().next() ).intValue();
//	}
}
