package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.AppContext;
import com.vathanakmao.libmgmt.exception.AlreadyExistsException;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.service.LibrarianService;
import com.vathanakmao.libmgmt.util.WebUtil;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.validator.BorrowBookValidator;

public class BorrowBookServlet extends HttpServlet {
	private LibrarianService service;
	
	public BorrowBookServlet() {
		service = (LibrarianService) AppContext.getInstance().getComponent("librarianService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!new BorrowBookValidator().validate(req)) {
			WebUtil.forward("borrowBook.jsp", req, resp);
		    return;
		}
		
		String adminUsername = WebUtil.getAdminUsernameFromSession(req);
		String memberId = req.getParameter("memberId");
		String bookCode = req.getParameter("bookCode");
		try {
			service.borrowBook(adminUsername, memberId, bookCode);
			WebUtil.redirect("_admin/borrowSuccess.jsp", req, resp);
		} catch (AlreadyExistsException | NotFoundException e) {
			ParamValidator.addError("unknown", e.getMessage(), req);
			WebUtil.forward("borrowBook.jsp", req, resp);
		}
	}
}
