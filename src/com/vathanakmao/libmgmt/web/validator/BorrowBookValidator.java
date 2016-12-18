package com.vathanakmao.libmgmt.web.validator;

import javax.servlet.http.HttpServletRequest;

import com.vathanakmao.libmgmt.web.constraint.Constraints;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;

public class BorrowBookValidator extends ParamValidator {

	@Override
	public boolean validate(HttpServletRequest req) {
		boolean memberIdValid = validate("memberId", req, Constraints.MANDATORY_CONSTRAINT);
		boolean bookCodeValid = validate("bookCode", req, Constraints.MANDATORY_CONSTRAINT);
		return memberIdValid & bookCodeValid;
	}

}
