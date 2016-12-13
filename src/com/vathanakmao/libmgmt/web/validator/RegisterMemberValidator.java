package com.vathanakmao.libmgmt.web.validator;

import javax.servlet.http.HttpServletRequest;

import com.vathanakmao.libmgmt.web.constraint.Constraints;
import com.vathanakmao.libmgmt.web.constraint.EqualConstraint;
import com.vathanakmao.libmgmt.web.constraint.MaxCharacterConstraint;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.constraint.PasswordConstraint;

public class RegisterMemberValidator extends ParamValidator {

	@Override
	public boolean validate(HttpServletRequest req) {
		boolean idValid = validate("id", req, Constraints.MANDATORY_CONSTRAINT);
		boolean firstNameValid = validate("firstName", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean lastNameValid = validate("lastName", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean sexValid = validate("sex", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean addressValid = validate("address", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(120));
		boolean passwordValid = validate("password", req, new PasswordConstraint());
		boolean confirmPasswordValid = validate("confirmPassword", req, Constraints.MANDATORY_CONSTRAINT, new EqualConstraint(req.getParameter("password"), "Password does not match"));
		return idValid & firstNameValid & lastNameValid & sexValid & addressValid & passwordValid & confirmPasswordValid;
	}

}
