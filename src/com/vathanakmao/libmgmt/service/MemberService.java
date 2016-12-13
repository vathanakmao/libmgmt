package com.vathanakmao.libmgmt.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.exception.AlreadyExistsException;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.util.SecurityUtil;

public class MemberService {
	private MemberDao memberDao;

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
}
