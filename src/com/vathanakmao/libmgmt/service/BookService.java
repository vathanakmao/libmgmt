package com.vathanakmao.libmgmt.service;

import java.util.List;

import com.vathanakmao.libmgmt.dao.BookDao;
import com.vathanakmao.libmgmt.model.Book;

public class BookService {
	private BookDao bookDao;
	
	public List<Book> search(String columnName, String columnValue) {
		return bookDao.findLike(columnName, columnValue);
	}
	
	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
