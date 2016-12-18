package com.vathanakmao.libmgmt.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.vathanakmao.libmgmt.dao.BookDao;
import com.vathanakmao.libmgmt.dao.BookRowMapper;
import com.vathanakmao.libmgmt.dao.BorrowDao;
import com.vathanakmao.libmgmt.dao.BorrowRowMapper;
import com.vathanakmao.libmgmt.dao.LibrarianDao;
import com.vathanakmao.libmgmt.dao.LibrarianRowMapper;
import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.exception.AlreadyExistsException;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.model.Book;
import com.vathanakmao.libmgmt.model.Borrow;
import com.vathanakmao.libmgmt.model.Librarian;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.util.SecurityUtil;

public class LibrarianService {
	public static final Integer MAX_BOOKS_BORROWED = 2;
	
	private LibrarianDao librarianDao;
	private MemberDao memberDao;
	private BookDao bookDao;
	private BorrowDao borrowDao;

	public LibrarianService(LibrarianDao librarianDao, MemberDao memberDao, BookDao bookDao, BorrowDao borrowDao) {
		this.librarianDao = librarianDao;
		this.memberDao = memberDao;
		this.bookDao = bookDao;
		this.borrowDao = borrowDao;
	}

	public LibrarianDao getLibrarianDao() {
		return librarianDao;
	}

	public void setLibrarianDao(LibrarianDao librarianDao) {
		this.librarianDao = librarianDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public BorrowDao getBorrowDao() {
		return borrowDao;
	}

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	public Librarian login(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
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

	public Borrow borrowBook(String username, String memberId, String bookCode) throws NotFoundException, AlreadyExistsException {
		Librarian librarian = librarianDao.getUniqueBy(LibrarianRowMapper.COLUMN_USERNAME, username);
		if (librarian == null) {
			System.out.println(String.format(">> Admin %s does not exist", username));
			throw new NotFoundException(String.format("Admin %s does not exist", username));
		}
		
		Member member = memberDao.getById(memberId);
		if (member == null) {
			System.out.println(String.format("Member %s does not exist", memberId));
			throw new NotFoundException(String.format("Member %s does not exist", memberId));
		}
		
		Book book = bookDao.getUniqueBy(BookRowMapper.COLUMN_CODE, bookCode);
		if (book == null || book.getStock() == null || book.getStock() == 0) {
			System.out.println(String.format(">> Book %s is out of stock or does not exist.", book.getCode()));
			throw new NotFoundException(String.format("Book %s does not exist", book.getCode()));
		}
		
		int countNotReturnedBooks = 0;
		List<Borrow> borrows = borrowDao.getBy(BorrowRowMapper.COLUMN_MEMBER_ID, member.getId());
		System.out.println(">> Borrows size: " + borrows.size());
		for (Borrow borrow : borrows) {
			
			// check if it this type of book is already being borrowed by this member
			if (borrow.getDateReturned() == null) {
				if (borrow.getBookId().equals(book.getId())) {
					System.out.println(String.format(">> This book (code=%s) is already being borrowed by this member (id=%s).", book.getCode(),  member.getId()));
					throw new AlreadyExistsException(String.format("This book (code=%s) is already being borrowed by this member (id=%s).", book.getCode(),  member.getId()));
				}
				
				countNotReturnedBooks++;
			}
			System.out.println(">> Number of borrowing books: " + countNotReturnedBooks);
			
			// check if it reaches max number of books borrowed
			if (countNotReturnedBooks == MAX_BOOKS_BORROWED) {
				System.out.println(String.format(">> This member is borrowing %s books. He/she can't borrow more", countNotReturnedBooks));
				throw new AlreadyExistsException(String.format("This member is borrowing %s books. He/she can't borrow more", countNotReturnedBooks)); 
			}
		}
		
		// update borrow table
		Borrow borrow = new Borrow(librarian.getId(), member.getId(), book.getId(), new Date());
		borrowDao.save(borrow);
		System.out.println(">> Borrow saved");
		
		// decrease stock in book table by one
		book.setStock(book.getStock() - 1);
		bookDao.update(book);
		System.out.println(String.format(">> Book updated (id=%s, stock=%s)", book.getId(), book.getStock()));
		
		return borrow;
	}
}
