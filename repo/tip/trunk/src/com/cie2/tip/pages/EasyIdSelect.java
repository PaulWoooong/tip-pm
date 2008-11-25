package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.PropertyAccess;

import com.cie2.tip.entities.Person;
import com.cie2.tip.models.EasyIdSelectModel;
import com.cie2.tip.models.IdSelectModel;
import com.cie2.tip.services.PersonService;

public class EasyIdSelect {

	@SuppressWarnings("unused")
	@Property
	@Persist
	private EasyIdSelectModel<Person> _personIds;

	@Property
	private Long _personId;
	
	@SuppressWarnings("unused")
	@Property
	private Person _person;
	
	@Inject
	private PersonService personService;
	
	@Inject
	private PropertyAccess _propertyAccess;
	
	Long onPassivate() {
		return _personId;
	}

	void onActivate(Long personId) {
		_personId = personId;
	}

	void onActivate() {
		refreshPersons();
	}

	void refreshPersons() {
		// Get all persons - ask business service to find them (from the database)
		List<Person> persons = personService.findPersons();
		_personIds = new EasyIdSelectModel<Person>(persons, Person.class, "firstName", "id", _propertyAccess);
	}
//
//	private IPersonServiceLocal getPersonService() {
//		// Use our business services locator to get the EJB3 session bean called "PersonServiceLocal".
//		return _businessServicesLocator.getPersonServiceLocal();
//	}
	
}
