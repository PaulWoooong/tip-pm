package com.cie2.tip.pages;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.services.ItemService;

/*
@IncludeStylesheet("context:extjs/resources/css/ext-all.css")
@IncludeJavaScriptLibrary({"context:extjs/ext-base.js",
							"context:extjs/ext-all.js",
							"context:extjs/ext-lang-zh_CN.js",
							"context:demo/grid-paging.js"})					
*/

public class MyGrid extends CieUserPage{
	@Inject
	@Service(value="itemService")
	private ItemService itemService;    //spring bean
	
	@Inject
	private RequestGlobals requestGlobals;
	
	@Property
	private TaskItem taskItem;
	
	private MyGridDataSource data;
	
	@Component(id="grid")
	private Grid grid;
	
	
	public MyGrid() {
		super();
		data = new MyGridDataSource(itemService, 0, 10);
	}
	
	public MyGridDataSource getTaskItems() {		
		data.setPage(grid.getCurrentPage() - 1 );
		return data;
	}
	
       /**
        *called by extjs ,through url here
        */
	@OnEvent(component="getGridData")
	public StreamResponse getGridData(){

		Request request = requestGlobals.getRequest();
		String start = request.getParameter("start");   //start index
		String limit = request.getParameter("limit");     //limit record num
		String sort = request.getParameter("sort");     //desc or asc
		String dir = request.getParameter("dir");         //property for sort

//		List<TaskItem> itemList = itemService.getItemList("0","5");  //the first load 
//		if(start!=null&&limit!=null)
//		{	
//			itemList = itemService.getItemList(start,limit);
//		}
//	
//		JSONArray record = new JSONArray();
//		for(int i=0;i<itemList.size();i++){
//			Item item = itemList.get(i);
//			JSONObject jsObject = new JSONObject();
//			jsObject.put("id", item.getId());
//			jsObject.put("name", item.getName());
//			jsObject.put("mobile", item.getMobile());
//			jsObject.put("email", item.getEmail());
//			jsObject.put("phone", item.getPhone());
//			record.add(jsObject);
//		}
//		
		JSONObject resp = new JSONObject();
//		resp.put("total", itemService.getItemListCount());
//		resp.put("results", record);

//		return new JsonStreamResponse(resp.toString());
		return null;
	}
}