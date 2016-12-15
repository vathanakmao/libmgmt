package com.vathanakmao.libmgmt.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.exception.AlreadyExistsException;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.util.SecurityUtil;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void register(Member member) throws AlreadyExistsException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if (memberDao.getById(member.getId()) != null) {
			throw new AlreadyExistsException(String.format("Member (ID=%s) already exists!", member.getId()));
		}
		member.setSalt(SecurityUtil.getRandomText());
		member.setPassword(SecurityUtil.hashMD5(member.getPassword(), member.getSalt()));
		memberDao.save(member);
	}
	
	public Member login(String id, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Member member = memberDao.getById(id);
		if (member == null) {
			throw new NotFoundException(String.format("Member (ID=%s) does not exist", id));
		}
		final String hashedPassword = SecurityUtil.hashMD5(password, member.getSalt());
		if (hashedPassword.equals(member.getPassword())) {
			return member;
		} 
		return null;
	}
}
