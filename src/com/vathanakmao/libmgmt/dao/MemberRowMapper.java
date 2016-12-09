package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.model.Sex;

public class MemberRowMapper extends RowMapper<Member> {

	@Override
	public Member toPojo(ResultSet rs) throws SQLException {
		Member pojo = new Member();
		pojo.setId(rs.getString("id"));
		pojo.setFirstName(rs.getString("first_name"));
		pojo.setLastName(rs.getString("last_name"));
		pojo.setSex(Sex.fromString(rs.getString("sex")));
		return pojo;
	}

	
}
