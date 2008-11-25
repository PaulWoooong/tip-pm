package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.Category;

public class ListCategory extends CieUserPage {
	static Logger logger =
	       Logger.getLogger(ListCategory.class.getName());
	
	@Property
	private Category category;
	
	@Inject
	private Session session;
	
	
	public List<Category> getCategories() {		
		return session.createCriteria(Category.class).list();
	}

}
