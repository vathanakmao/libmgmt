package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.util.WebUtil;

public class AdminLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.getSession().removeAttribute("librarianUsername");
		WebUtil.redirect("_admin/login.jsp", req, resp);
	}
}
