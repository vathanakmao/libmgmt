package com.vathanakmao.libmgmt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Book;
import com.vathanakmao.libmgmt.model.Member;

public class MemberDao extends GenericDao<Member, String> {
	
	public MemberDao() {
		super("member", new MemberRowMapper());
	}
	
	@Override
	protected String generateSqlInsert(Member e) {
		return new StringBuffer()
			.append("insert into ")
			.append(getTableName())
			.append("(id, first_name, last_name, sex, address, salt, password) values ('")
			.append(e.getId())
			.append("','")
			.append(e.getFirstName())
			.append("','")
			.append(e.getLastName())
			.append("','")
			.append(e.getSex().toShortString())
			.append("','")
			.append(e.getAddress())
			.append("','")
			.append(e.getSalt())
			.append("','")
			.append(e.getPassword())
			.append("')")
			.toString();
	}

	@Override
	protected String generateSqlUpdate(Member e) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void setId(Member e, PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			e.setId(rs.getString(1));
		}
	}
}
