package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.model.Sex;

public class MemberRowMapper extends RowMapper<Member> {
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_FIRSTNAME = "first_name";
	public static final String COLUMN_LASTNAME = "last_name";
	public static final String COLUMN_SEX = "sex";

	@Override
	public Member toPojo(ResultSet rs) throws SQLException {
		Member pojo = new Member();
		pojo.setId(rs.getString(COLUMN_ID));
		pojo.setFirstName(rs.getString(COLUMN_FIRSTNAME));
		pojo.setLastName(rs.getString(COLUMN_LASTNAME));
		pojo.setSex(Sex.fromString(rs.getString(COLUMN_SEX)));
		return pojo;
	}

	
}
