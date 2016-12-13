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
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_SALT = "salt";
	public static final String COLUMN_PASSWORD = "password";
	

	@Override
	public Member toPojo(ResultSet rs) throws SQLException {
		Member pojo = new Member();
		pojo.setId(rs.getString(COLUMN_ID));
		pojo.setFirstName(rs.getString(COLUMN_FIRSTNAME));
		pojo.setLastName(rs.getString(COLUMN_LASTNAME));
		pojo.setSex(Sex.fromString(rs.getString(COLUMN_SEX)));
		pojo.setAddress(rs.getString(COLUMN_ADDRESS));
		pojo.setSalt(rs.getString(COLUMN_SALT));
		pojo.setPassword(rs.getString(COLUMN_PASSWORD));
		return pojo;
	}

	
}
