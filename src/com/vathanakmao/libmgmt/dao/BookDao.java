package com.vathanakmao.libmgmt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	@Override
	protected String generateSqlUpdate(Book e) {
		return new StringBuffer()
				.append("update ")
				.append(getTableName())
				.append(" set title='")
				.append(e.getTitle())
				.append("', author='")
				.append(e.getAuthor())
				.append("', code='")
				.append(e.getCode())
				.append("', year=")
				.append(e.getYear())
				.append(", stock=")
				.append(e.getStock())
				.append(" where id=")
				.append(e.getId())
				.toString();
	}

	@Override
	protected void setId(Book e, PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			e.setId(rs.getLong(1));
		}
	}
}
