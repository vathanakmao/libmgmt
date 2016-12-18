package com.vathanakmao.libmgmt.dao;

import com.vathanakmao.libmgmt.model.Librarian;

public class LibrarianDao extends GenericDao<Librarian, Long> {
	
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

}
