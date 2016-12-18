package com.vathanakmao.libmgmt.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtil {

	public static void forward(String pageName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = null;
		if (!req.getContextPath().equals("") && req.getRequestURI().startsWith(req.getContextPath())) {
			page = pageName;
		} else {
			page = req.getContextPath() + "/" + pageName;
		}
		RequestDispatcher rd = req.getRequestDispatcher(page);
	    rd.forward(req, resp);
	}
	
	public static void redirect(String pageName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String page = null;
//		if (!req.getContextPath().equals("") && req.getRequestURI().startsWith(req.getContextPath())) {
//			page = pageName;
//		} else {
//			page = req.getContextPath() + "/" + pageName;
//		}
		resp.sendRedirect(req.getContextPath() + "/" + pageName);
	}
	
	public static void createMemberSession(String id, HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		session.setAttribute("memberId", id);
	}
	
	public static void createAdminSession(String username, HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		session.setAttribute("librarianUsername", username);
	}
	
	public static String getMemberIdFromSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return  (String) session.getAttribute("memberId");
	}
	
	public static String getAdminUsernameFromSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (String) session.getAttribute("librarianUsername");
	}
	
	public static boolean isMemberLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("memberId") != null) {
			return true;
		}
		return false;
	}
	
	public static boolean isAdminLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("librarianUsername") != null) {
			return true;
		}
		return false;
	}
}
