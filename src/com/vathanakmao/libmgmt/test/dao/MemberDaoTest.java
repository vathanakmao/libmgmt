package com.vathanakmao.libmgmt.test.dao;

import java.sql.SQLException;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.model.Sex;

public class MemberDaoTest {
	private MemberDao dao;
	
	public MemberDaoTest() {
		dao = new MemberDao();
	}
	
	public static void main(String[] args) throws SQLException {
		MemberDaoTest test = new MemberDaoTest();
//		test.testSave();
		test.testGetById();
	}
	
	public void testGetById() throws SQLException {
		Member entity = dao.getById("g5938444");
		System.out.println("Entity <id=" + entity.getId() + ", firstname=" + entity.getFirstName() + ">");
	}

	public void testSave() throws SQLException {
		long currentTimeMillis = System.currentTimeMillis();
		Member e = new Member();
		e.setId(String.valueOf(currentTimeMillis));
		e.setFirstName("Vathanak" + currentTimeMillis);
		e.setLastName("Mao" + currentTimeMillis);
		e.setSex(Sex.MALE);
		dao.save(e);
	}
}
