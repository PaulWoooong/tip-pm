package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.ItemService;

public class MyGridDataSource implements GridDataSource {

	List data; 
	int rows;
	int rowsPerPage;
	int page;
	ItemService service;
	
	public MyGridDataSource(ItemService service, int page, int rowsPerPage) {
		System.out.println("=== Constructing ");		
//		this.data = data;
		data = service.getItemList((page*rowsPerPage),rowsPerPage);
//		this.rows = rows;
		this.rowsPerPage = rowsPerPage;
		this.service = service;
		this.page = page;
	}

	
	public int getAvailableRows() {
		rows = service.getItemListCount();
		System.out.println("=== Getting rows with size " + data.size() + " total rows " + rows);		
		return rows;
	}

	public Class getRowType() {
		System.out.println("=== Get Row Type ");				
		return TaskItem.class;
	}

	public Object getRowValue(int index) {
		System.out.println("Getting value for row " + index);		
		return data.get(index%rowsPerPage);
	}

	public void prepare(int start, int end, List<SortConstraint> sort) {
		System.out.println("preparing start " + start + " end " + end + " sort " + sort);
		System.out.println("page " + page + " rowsPerPage " + rowsPerPage);
		data = service.getItemList((page*rowsPerPage),rowsPerPage);
	}

	public void setPage(int page) {
		this.page = page;
	}
}
