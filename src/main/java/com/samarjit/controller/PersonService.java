package com.samarjit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.samarjit.dao.PersonDao;
import com.samarjit.dto.Person;

@RestController
public class PersonService {

	@Autowired
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/people")
	public @ResponseBody List<Person> fetchAll() {
		return personDao.getAllPersons();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/people/{id}")
	public ResponseEntity<Person> getEmployeeById(@PathVariable("id") int id) {
		Person person = personDao.getPersonById(id);
		if (person != null)
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
	}
}