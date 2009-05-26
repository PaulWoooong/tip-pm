package com.cie2.tip.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.TaskItem.TaskStatus;
import com.cie2.tip.models.Item;

/**
 * 
 * @author  Fanzhen.Meng
 * @version
 *		    10:24:49 AM
 *          2008-3-26
 */
@SuppressWarnings("unchecked")
public class ItemService {
	
	private Session session;
	
	public ItemService(Session session) {
		this.session = session;
	}

	public List<TaskItem> getItemList(){
		Criteria criteria =  session.createCriteria(TaskItem.class);
		
		List<TaskItem> itemList = criteria.list();
		return itemList;
	}

	public List<TaskItem> getItemList(int start,int limit){
		Criteria criteria =  session.createCriteria(TaskItem.class);
		
		criteria.setFirstResult(Integer.valueOf(start));
		criteria.setMaxResults(limit);
//		criteria.setMaxResults(Integer.valueOf(limit)-Integer.valueOf(start)+1);
		List<TaskItem> itemList = criteria.list();
		return itemList;
	}
	
	public int getItemListCount(){
		String hql = "select count(id) from TaskItem where 1=1";
		Query  query = this.session.createQuery(hql);
		int count  = ((Long)query.uniqueResult()).intValue();
		return count;
	}
}