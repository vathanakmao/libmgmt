package com.vathanakmao.libmgmt.dao;

import com.vathanakmao.libmgmt.model.Book;

public class BookDao extends GenericDao<Book, Long> {
	
	public BookDao() {
		super("book", new BookRowMapper());
	}
	
	@Override
	protected String generateSqlInsert(Book e) {
		return new StringBuffer()
			.append("insert into ")
			.append(getTableName())
			.append("(id, code, title, author, year) values (")
			.append(e.getId())
			.append(",'")
			.append(e.getCode())
			.append("','")
			.append(e.getTitle())
			.append("','")
			.append(e.getAuthor())
			.append("',")
			.append(e.getYear())
			.append(")")
			.toString();
	}

}
