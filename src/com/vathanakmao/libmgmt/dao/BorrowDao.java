package com.vathanakmao.libmgmt.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Borrow;

public class BorrowDao extends GenericDao<Borrow, Long> {
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public BorrowDao() {
		super("borrow", new BorrowRowMapper());
	}
	
	@Override
	protected String generateSqlInsert(Borrow e) {
		return new StringBuffer()
			.append("insert into ")
			.append(getTableName())
			.append("(librarian_id, member_id, book_id, date_borrowed) values (")
			.append(e.getLibrarianId())
			.append(",'")
			.append(e.getMemberId())
			.append("',")
			.append(e.getBookId())
			.append(",'")
			.append(sdf.format(e.getDateBorrowed()))
			.append("')")
			.toString();
	}

	@Override
	protected String generateSqlUpdate(Borrow e) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void setId(Borrow e, PreparedStatement stmt) throws SQLException {
		// it uses compound primary key
	}
}
