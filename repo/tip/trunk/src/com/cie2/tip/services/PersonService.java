package com.cie2.tip.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cie2.tip.entities.Person;

public class PersonService {

	@PersistenceContext(unitName = "jumpstart")
	protected EntityManager _em;

	public Person findPerson(Long id) {
		return _em.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findPersons() {
		
		Person personA = new Person();
		personA.setFirstName("A");
		personA.setLastName("A");
		personA.setStartDate(new Date());
		personA.setVersion(1);

		Person personB = new Person();
		personB.setFirstName("B");
		personB.setLastName("B");
		personB.setStartDate(new Date());
		personB.setVersion(2);

		Person personC = new Person();
		personC.setFirstName("C");
		personC.setLastName("C");
		personC.setStartDate(new Date());
		personC.setVersion(2);
		
		ArrayList persons = new ArrayList();
		persons.add(personA);
		persons.add(personB);
		persons.add(personC);
		return persons;
//		return _em.createQuery("select p from Person p order by p.id").getResultList();
	}

	public Person createPerson(Person person) {
		_em.persist(person);
		return person;
	}

	public void createPersons(List<Person> persons) {
		for (Person person : persons) {
			_em.persist(person);
		}
	}

	public void changePerson(Person person) {
		_em.merge(person);
	}

	public void changePersons(List<Person> persons) {
		for (Person person : persons) {
			_em.merge(person);
		}
	}

	public void bulkEditPersons(List<Person> personsToCreate, List<Person> personsToChange, List<Person> personsToDelete) {
		for (Person person : personsToCreate) {
			_em.persist(person);
		}
		for (Person person : personsToChange) {
			_em.merge(person);
		}
		for (Person person : personsToDelete) {
			person = _em.merge(person);
			_em.remove(person);
		}
	}
}

