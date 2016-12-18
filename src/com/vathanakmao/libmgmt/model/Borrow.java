package com.vathanakmao.libmgmt.model;

import java.util.Date;

public class Borrow {
	private Integer librarianId;
	private String memberId;
	private Long bookId;
	private Date dateBorrowed;
	private Date dateReturned;
	
	public Borrow() {
	}
	
	public Borrow(Integer librarianId, String memberId, Long bookId, Date dateBorrowed) {
		this.librarianId = librarianId;
		this.memberId = memberId;
		this.bookId = bookId;
		this.bookId = bookId;
		this.dateBorrowed = dateBorrowed;
	}

	public Integer getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(Integer librarianId) {
		this.librarianId = librarianId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

}
