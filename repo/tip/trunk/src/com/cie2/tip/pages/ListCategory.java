package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Category;
import com.cie2.tip.entities.TaskItem;

public class ListCategory extends CieUserPage {
	static Logger logger =
	       Logger.getLogger(ListCategory.class.getName());
	
	@Property
	private Category category;
	
	@Inject
	private Session session;
	
	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;

	
	public List<Category> getCategories() {		
		return session.createCriteria(Category.class).list();
	}

	public BeanModel getModel() {
		BeanModel model = beanModelSource.create(Category.class, false,
				messages);
		model.add("parentCategory", null);
		return model;
	}	
}
