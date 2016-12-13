package com.vathanakmao.libmgmt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	private String resourcePath;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		resourcePath = config.getInitParameter("resource-path");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
        if (path.toLowerCase().startsWith(resourcePath)) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
    		System.out.println("Accessing URI " + ((HttpServletRequest) request).getRequestURI());
            chain.doFilter(request, response);
        }
	}

	@Override
	public void destroy() {
	}

	
}
