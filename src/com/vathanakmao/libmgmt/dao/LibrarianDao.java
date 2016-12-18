package com.vathanakmao.libmgmt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Book;
import com.vathanakmao.libmgmt.model.Librarian;

public class LibrarianDao extends GenericDao<Librarian, Integer> {
	
	public LibrarianDao() {
		super("librarian", new LibrarianRowMapper());
	}
	
	@Override
	protected String generateSqlInsert(Librarian e) {
		return new StringBuffer()
			.append("insert into ")
			.append(getTableName())
			.append("(username, first_name, last_name, sex, salt, password) values (")
			.append("'")
			.append(e.getUsername())
			.append("','")
			.append(e.getFirstName())
			.append("','")
			.append(e.getLastName())
			.append("','")
			.append(e.getSalt())
			.append("','")
			.append(e.getPassword())
			.append("')")
			.toString();
	}
	
	@Override
	protected String generateSqlUpdate(Librarian e) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void setId(Librarian e, PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			e.setId(rs.getInt(1));
		}
	}

}
