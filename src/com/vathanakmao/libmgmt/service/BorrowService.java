package com.vathanakmao.libmgmt.service;

import java.util.ArrayList;
import java.util.List;

import com.vathanakmao.libmgmt.dao.BookDao;
import com.vathanakmao.libmgmt.dao.BorrowDao;
import com.vathanakmao.libmgmt.dao.BorrowRowMapper;
import com.vathanakmao.libmgmt.model.Borrow;
import com.vathanakmao.libmgmt.model.BorrowedBookVo;

public class BorrowService {
	private BorrowDao borrowDao;
	private BookDao bookDao;
	
	public BorrowService(BorrowDao borrowDao, BookDao bookDao) {
		this.borrowDao = borrowDao;
		this.bookDao = bookDao;
	}
	
	public BorrowDao getBorrowDao() {
		return borrowDao;
	}

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<BorrowedBookVo> getBorrowedBooks(String memberId) {
		List<BorrowedBookVo> borrowedBooks = new ArrayList<BorrowedBookVo>();

		List<Borrow> borrows = borrowDao.getBy(BorrowRowMapper.COLUMN_MEMBER_ID, memberId);
		System.out.println(">> Member's borrowed books: " + borrows.size());
		for (Borrow borrow : borrows) {
			if (borrow.getDateReturned() == null) {
				BorrowedBookVo borrowedBook = new BorrowedBookVo();
				borrowedBook.setBorrow(borrow);
				borrowedBook.setBook(bookDao.getById(borrow.getBookId()));
				System.out.println(">> Borrowed book: " + borrow.getBookId());
				
				borrowedBooks.add(borrowedBook);
			}
		}
		return borrowedBooks;
	}
}
