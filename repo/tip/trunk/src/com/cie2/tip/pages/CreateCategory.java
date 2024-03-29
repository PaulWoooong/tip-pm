package com.cie2.tip.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.cie2.tip.entities.Category;
import com.cie2.tip.models.EasyIdSelectModel;
import com.cie2.tip.services.CategoryService;
import com.cie2.tip.services.ProjectService;

public class CreateCategory  {

	static Logger logger =
	       Logger.getLogger(CreateCategory.class.getName());

	@SuppressWarnings("unused")
	@Property
	@Persist
	private EasyIdSelectModel<Category> categoryModels;

	@Property
	private Long _categoryId;

	@Property
	private Category category;
	
	
    // services
    @Inject
	private CategoryService categoryService;
	
    @Inject
    private ProjectService projectServices;
    
	@Inject
	private PropertyAccess _propertyAccess;
	
	Long onPassivate() {
		return _categoryId;
	}

	void onActivate(Long categoryId) {
		_categoryId = categoryId;
	}

	/**
	 * kenapa kalo extends cie user page, selectnya gak nongol ? 
	 * gara2 on activatenya ada di base kah ? 
	 *
	 */
	void onActivate() {
		refreshCategory();
	}

	void refreshCategory() {
		List<Category> categories = categoryService.findCategory(projectServices.getDefaultProject());
		logger.info("Refresh persons size : " + categories.size());
		categoryModels = new EasyIdSelectModel<Category>(categories, Category.class, "name", "id", _propertyAccess);
	}
    
    
    @CommitAfter
    Object onSuccess()
    {
    	logger.info("Parent Category id : " + _categoryId);
    	if (_categoryId != null) {
			Category parentCategory = categoryService.findCategory(_categoryId);
			category.setParentCategory(parentCategory);
		}
    	category.setProject(projectServices.getDefaultProject());
    	categoryService.add(category);
        return ListCategory.class;
    }
	
	
}
