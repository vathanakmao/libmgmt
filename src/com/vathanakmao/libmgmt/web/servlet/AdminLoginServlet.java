package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.AppContext;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.model.Librarian;
import com.vathanakmao.libmgmt.service.LibrarianService;
import com.vathanakmao.libmgmt.util.WebUtil;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.validator.AdminLoginValidator;

public class AdminLoginServlet extends HttpServlet {
	private LibrarianService service;
	
	public AdminLoginServlet() {
		service = (LibrarianService) AppContext.getInstance().getComponent("librarianService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!new AdminLoginValidator().validate(req)) {
			WebUtil.forward("_admin/login.jsp", req, resp);
		    return;
		}

		final String username = req.getParameter("username");
		final String plainPassword = req.getParameter("password");
		try {
			Librarian librarian = service.login(username, plainPassword);
			if (librarian != null) {
				WebUtil.createLibrarianSession(username, req);
				WebUtil.redirect("_admin/profile.jsp", req, resp);
			} else {
				ParamValidator.addError("unknown", "Invalid Password" , req);
				WebUtil.forward("_admin/login.jsp", req, resp);
			}
		} catch (NotFoundException e) {
			ParamValidator.addError("unknown", e.getMessage(), req);
			WebUtil.forward("_admin/login.jsp", req, resp);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}
}
