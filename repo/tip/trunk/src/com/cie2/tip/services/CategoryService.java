package com.cie2.tip.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cie2.tip.entities.Category;
import com.cie2.tip.entities.Project;

public class CategoryService {

	protected EntityManager _em;

	private Session _session;
	
	public CategoryService(Session session) {
		_session = session;
	}
	
	public Category findCategory(Long id) {
		return (Category) _session.get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findCategory(Project project) {
		
		List categories = _session.createCriteria(Category.class).add(
				Restrictions.eq("project", project)).list();

		return categories;		
	}
}
