package com.vathanakmao.libmgmt.dao;

import com.vathanakmao.libmgmt.model.Member;

public class MemberDao extends GenericDao<Member, String> {
	
	public MemberDao() {
		super(new MemberRowMapper());
	}

	private static final String TABLE_NAME = "member";
	
	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	protected String generateInsertSql(Member e) {
		return new StringBuffer()
			.append("insert into ")
			.append(getTableName())
			.append("(id, first_name, last_name, sex) values ('")
			.append(e.getId())
			.append("','")
			.append(e.getFirstName())
			.append("','")
			.append(e.getLastName())
			.append("','")
			.append(e.getSex().toShortString())
			.append("')")
			.toString();
	}

}
