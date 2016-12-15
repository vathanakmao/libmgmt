package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Book;

public class BookRowMapper extends RowMapper<Book> {
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_YEAR = "year";
	

	@Override
	public Book toPojo(ResultSet rs) throws SQLException {
		Book pojo = new Book();
		pojo.setId(rs.getString(COLUMN_ID));
		pojo.setCode(rs.getString(COLUMN_CODE));
		pojo.setTitle(rs.getString(COLUMN_TITLE));
		pojo.setAuthor(rs.getString(COLUMN_AUTHOR));
		pojo.setYear(rs.getInt(COLUMN_YEAR));
		return pojo;
	}

	
}
