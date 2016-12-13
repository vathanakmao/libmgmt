package com.vathanakmao.libmgmt.test.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.dao.MemberRowMapper;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.model.Sex;
import com.vathanakmao.libmgmt.util.SecurityUtil;

public class MemberDaoTest {
	private MemberDao dao;
	
	public MemberDaoTest() {
		dao = new MemberDao();
	}
	
	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		MemberDaoTest test = new MemberDaoTest();
		test.testSave();
//		test.testGetById();
//		test.testGetByColumnName();
	}
	
	public void testGetById() throws SQLException {
		Member entity = dao.getById("g5938444");
		System.out.println("Entity <id=" + entity.getId() + ", firstname=" + entity.getFirstName() + ">");
	}
	
	public void testGetByColumnName() throws SQLException {
		List<Member> entities = dao.getBy(MemberRowMapper.COLUMN_FIRSTNAME, "Vathanak");
		System.out.println("Entities: " + entities.size());
	}

	public void testSave() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		long currentTimeMillis = System.currentTimeMillis();
		Member e = new Member();
		e.setId(String.valueOf(currentTimeMillis));
		e.setFirstName("Vathanak" + currentTimeMillis);
		e.setLastName("Mao" + currentTimeMillis);
		e.setSex(Sex.MALE);
		e.setAddress("Phnom Penh");
		e.setSalt(SecurityUtil.getRandomText());
		e.setPassword(SecurityUtil.hashMD5("12345A", e.getSalt()));
		dao.save(e);
		
		Member m = dao.getById(e.getId());
		System.out.println(">> Passwords match: " + SecurityUtil.hashMD5("12345A", e.getSalt()).equals(m.getPassword()));

	}
}
