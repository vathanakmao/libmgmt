package com.vathanakmao.libmgmt.web.constraint;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class ParamValidator {
	private static final String ATTRIBUTE_ERROR = "errors";
	
	public abstract boolean validate(HttpServletRequest request);

	@SuppressWarnings("unchecked")
	protected boolean validate(String paramName, HttpServletRequest req, ParamConstraint ... constraints) {
		Map<String,String> errors = (Map<String, String>) req.getAttribute(ATTRIBUTE_ERROR);
		if (errors == null) {
			errors = new HashMap<String,String>();
		}
		
		String val = req.getParameter(paramName);
		for (ParamConstraint constraint: constraints) {
			String errMsg = constraint.check(val);
			if (errMsg != null) {
				errors.put(paramName, errMsg);
				req.setAttribute(ATTRIBUTE_ERROR, errors);
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static void addError(String name, String message, HttpServletRequest req) {
		Map<String,String> errors = (Map<String, String>) req.getAttribute(ATTRIBUTE_ERROR);
		if (errors == null) {
			errors = new HashMap<String,String>();
		}
		errors.put(name, message);
		req.setAttribute(ATTRIBUTE_ERROR, errors);
	}
}
