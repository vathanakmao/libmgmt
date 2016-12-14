package com.vathanakmao.libmgmt.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtil {

	public static void forward(String pageName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(pageName);
	    rd.forward(req, resp);
	}
	
	public static void redirect(String pageName, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(pageName);
	}
	
	public static void createMemberSession(String id, HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		session.setAttribute("memberId", id);
	}
	
	public static boolean isMemberLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("memberId") != null) {
			return true;
		}
		return false;
	}
}
