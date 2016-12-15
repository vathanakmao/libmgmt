package com.vathanakmao.libmgmt.web.validator;

import javax.servlet.http.HttpServletRequest;

import com.vathanakmao.libmgmt.web.constraint.Constraints;
import com.vathanakmao.libmgmt.web.constraint.MaxCharacterConstraint;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;

public class SearchBooksValidator extends ParamValidator {

	@Override
	public boolean validate(HttpServletRequest req) {
		boolean searchTextValid = validate("text", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(128));
		boolean searchFieldValid = validate("field", req, Constraints.MANDATORY_CONSTRAINT);
		return searchTextValid & searchFieldValid;
	}

}
