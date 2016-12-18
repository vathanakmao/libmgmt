package com.vathanakmao.libmgmt.model;

public class BorrowedBookVo {
	private Borrow borrow;
	private Book book;

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
