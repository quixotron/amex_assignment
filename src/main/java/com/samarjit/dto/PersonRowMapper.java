package com.samarjit.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper<Person> {
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("ID"));
		person.setName(rs.getString("NAME"));
		person.setAge(rs.getInt("AGE"));
		person.setDateOfBirth(rs.getDate("DATEOFBIRTH"));
		person.setEmail(rs.getString("EMAIL"));
		return person;
	}

}