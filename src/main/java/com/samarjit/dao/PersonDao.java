package com.samarjit.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samarjit.dto.Person;
import com.samarjit.dto.PersonRowMapper;

@Repository
public class PersonDao {
	private static Logger logger = LoggerFactory.getLogger(PersonDao.class);
	private static final String SQL_PERSON_BY_ID = "SELECT * FROM PERSON WHERE ID = ?";
	private static final String SQL_ALL_PERSONS = "SELECT * FROM PERSON";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Person getPersonById(int id) {
		Person person = null;
		try {
			person = jdbcTemplate.queryForObject(SQL_PERSON_BY_ID, new Object[] { id }, new PersonRowMapper());
		} catch (DataAccessException e) {
			logger.debug("Failed to fetch Person record for the given ID {}", id);
		}
		return person;
	}

	public List<Person> getAllPersons() {
		List<Person> personList = null;
		try {
			personList = jdbcTemplate.query(SQL_ALL_PERSONS, new PersonRowMapper());
		} catch (DataAccessException e) {
			logger.debug("Failed to fetch Person records");
		}
		return personList;
	}

}
