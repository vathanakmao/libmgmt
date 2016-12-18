package com.vathanakmao.libmgmt.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.vathanakmao.libmgmt.dao.LibrarianDao;
import com.vathanakmao.libmgmt.dao.LibrarianRowMapper;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.model.Librarian;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.util.SecurityUtil;

public class LibrarianService {
	private LibrarianDao librarianDao;

	public LibrarianService(LibrarianDao librarianDao) {
		this.librarianDao = librarianDao;
	}

	public LibrarianDao getLibrarianDao() {
		return librarianDao;
	}

	public void setLibrarianDao(LibrarianDao librarianDao) {
		this.librarianDao = librarianDao;
	}

	public Librarian login(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Librarian librarian = (Librarian) librarianDao.getUniqueBy(LibrarianRowMapper.COLUMN_USERNAME, username);
		if (librarian == null) {
			throw new NotFoundException(String.format("Librarian (%s) does not exist", username));
		}
		final String hashedPassword = SecurityUtil.hashMD5(password, librarian.getSalt());
		if (hashedPassword.equals(librarian.getPassword())) {
			return librarian;
		}
		return null;
	}
}
