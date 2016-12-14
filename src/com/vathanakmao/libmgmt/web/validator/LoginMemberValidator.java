package com.vathanakmao.libmgmt.web.validator;

import javax.servlet.http.HttpServletRequest;

import com.vathanakmao.libmgmt.web.constraint.Constraints;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;

public class LoginMemberValidator extends ParamValidator {

	@Override
	public boolean validate(HttpServletRequest req) {
		boolean idValid = validate("id", req, Constraints.MANDATORY_CONSTRAINT);
		boolean passwordValid = validate("password", req, Constraints.MANDATORY_CONSTRAINT);
		return idValid & passwordValid;
	}

}
