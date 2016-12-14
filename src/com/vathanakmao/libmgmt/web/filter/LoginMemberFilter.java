package com.vathanakmao.libmgmt.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.util.WebUtil;

public class LoginMemberFilter implements Filter {
	private List<String> ignoredPaths;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String ignoredPathsText = config.getInitParameter("ignored-paths");
		System.out.println(">> ignoredPathsText: " + ignoredPathsText);
		
		ignoredPaths = new ArrayList<String>();
		if (ignoredPathsText != null) {
			for (String path : ignoredPathsText.split(",")) {
				ignoredPaths.add(path);
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse resp = (HttpServletResponse) response;
		
		boolean isIgnoredPath = false;
		final String path = ((HttpServletRequest) req).getServletPath();
		final String lowerCasePath = path.toLowerCase();
		System.out.println(">> Servlet Path: " + path);
		for (String p : ignoredPaths) {
			if (lowerCasePath.startsWith(p.toLowerCase())) {
				isIgnoredPath = true;
	        	break;
	        } 
		}
		System.out.println(">> isIgnoredPath: " + isIgnoredPath);
		
		if (!isIgnoredPath && !WebUtil.isMemberLoggedIn(req)) {
        	WebUtil.redirect("loginMember.jsp", (HttpServletResponse) resp);
        } else {
        	chain.doFilter(req, resp);
        }
	}

	@Override
	public void destroy() {
	}

	
}
