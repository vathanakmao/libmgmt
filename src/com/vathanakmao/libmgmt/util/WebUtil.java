package com.vathanakmao.libmgmt.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static void forward(String pageName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/registerMember.jsp");
	    rd.forward(request, response);
	}
	
	public static void redirect(String pageName, HttpServletResponse response) throws IOException {
		response.sendRedirect(pageName);
	} 
}
