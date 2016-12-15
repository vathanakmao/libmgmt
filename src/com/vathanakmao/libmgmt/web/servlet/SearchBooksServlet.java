package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.AppContext;
import com.vathanakmao.libmgmt.dao.BookRowMapper;
import com.vathanakmao.libmgmt.model.Book;
import com.vathanakmao.libmgmt.service.BookService;
import com.vathanakmao.libmgmt.util.WebUtil;
import com.vathanakmao.libmgmt.web.validator.SearchBooksValidator;

public class SearchBooksServlet extends HttpServlet {
	private BookService bookService;
	
	public SearchBooksServlet() {
		bookService = (BookService) AppContext.getInstance().getComponent("bookService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (new SearchBooksValidator().validate(req)) {
			String columnName = getColumnName(req.getParameter("field"));
			String columnValue = req.getParameter("text");
			List<Book> books = bookService.search(columnName, columnValue);
			req.setAttribute("books", books);
		}
		WebUtil.forward("searchBooks.jsp", req, resp);
	}
	
	private String getColumnName(String field) {
		switch(field) {
		case "1": return BookRowMapper.COLUMN_TITLE;
		case "2": return BookRowMapper.COLUMN_CODE;
		case "3": return BookRowMapper.COLUMN_AUTHOR;
		case "4": return BookRowMapper.COLUMN_YEAR; 
		}
		throw new IllegalArgumentException("Invalid field parameter: " + field);
	}
}
