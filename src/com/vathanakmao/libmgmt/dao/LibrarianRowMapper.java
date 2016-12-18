package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Librarian;
import com.vathanakmao.libmgmt.model.Sex;

public class LibrarianRowMapper extends RowMapper<Librarian> {
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_FIRSTNAME = "first_name";
	public static final String COLUMN_LASTNAME = "last_name";
	public static final String COLUMN_SEX = "sex";
	public static final String COLUMN_SALT = "salt";
	public static final String COLUMN_PASSWORD = "password";
	

	@Override
	public Librarian toPojo(ResultSet rs) throws SQLException {
		Librarian pojo = new Librarian();
		pojo.setId(rs.getInt(COLUMN_ID));
		pojo.setUsername(rs.getString(COLUMN_USERNAME));
		pojo.setFirstName(rs.getString(COLUMN_FIRSTNAME));
		pojo.setLastName(rs.getString(COLUMN_LASTNAME));
		pojo.setSex(Sex.fromString(rs.getString(COLUMN_SEX)));
		pojo.setSalt(rs.getString(COLUMN_SALT));
		pojo.setPassword(rs.getString(COLUMN_PASSWORD));
		return pojo;
	}

	
}
