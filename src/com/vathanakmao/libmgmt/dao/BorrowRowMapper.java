package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Borrow;

public class BorrowRowMapper extends RowMapper<Borrow> {
	public static final String COLUMN_LIBRARIAN_ID = "librarian_id";
	public static final String COLUMN_MEMBER_ID = "member_id";
	public static final String COLUMN_BOOK_ID = "book_id";
	public static final String COLUMN_DATE_BORROWED = "date_borrowed";
	public static final String COLUMN_DATE_RETURNED = "date_returned";
	

	@Override
	public Borrow toPojo(ResultSet rs) throws SQLException {
		Borrow pojo = new Borrow();
		pojo.setLibrarianId(rs.getInt(COLUMN_LIBRARIAN_ID));
		pojo.setMemberId(rs.getString(COLUMN_MEMBER_ID));
		pojo.setBookId(rs.getLong(COLUMN_BOOK_ID));
		pojo.setDateBorrowed(rs.getDate(COLUMN_DATE_BORROWED));
		pojo.setDateReturned(rs.getDate(COLUMN_DATE_RETURNED));
		return pojo;
	}

	
}
