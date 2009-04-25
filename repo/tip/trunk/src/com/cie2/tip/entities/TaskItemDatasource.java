package com.cie2.tip.entities;

import java.util.List;

import javax.management.Query;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

public class TaskItemDatasource implements GridDataSource {

	private Query query;
	int availableRows;
	
	public TaskItemDatasource(Query query) {
		this.query = query;
//		this.availableRows = query.list().size();
	}
	
	public int getAvailableRows() {
		return availableRows;
	}

	public Class getRowType() {
		// XXX Auto-generated method stub
		return null;
	}

	public Object getRowValue(int arg0) {
		// XXX Auto-generated method stub
		return null;
	}

	public void prepare(int arg0, int arg1, List<SortConstraint> arg2) {
		// XXX Auto-generated method stub

	}

//	public void Query createCountQuery() {
//		( (Integer) session.createQuery("select count(*) from ....").iterate().next() ).intValue();
//	}
}
