package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.web.constraint.Constraints;
import com.vathanakmao.libmgmt.web.constraint.EqualConstraint;
import com.vathanakmao.libmgmt.web.constraint.MaxCharacterConstraint;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.constraint.PasswordConstraint;

public class RegisterMemberServlet extends HttpServlet {
	private ParamValidator validator;
	
	@Override
	public void init() throws ServletException {
		validator = new ParamValidator();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("memberId");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String sex = req.getParameter("sex");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		boolean idValid = validator.validate("id", req, Constraints.MANDATORY_CONSTRAINT);
		boolean firstNameValid = validator.validate("firstName", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean lastNameValid = validator.validate("lastName", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean sexValid = validator.validate("sex", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(30));
		boolean addressValid = validator.validate("address", req, Constraints.MANDATORY_CONSTRAINT, new MaxCharacterConstraint(120));
		boolean passwordValid = validator.validate("password", req, new PasswordConstraint());
		boolean confirmPasswordValid = validator.validate("confirmPassword", req, Constraints.MANDATORY_CONSTRAINT, new EqualConstraint(password, "Password does not match"));
		if (!(idValid && firstNameValid && lastNameValid && sexValid && addressValid && passwordValid)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerMember.jsp");
		    rd.forward(req, resp);
		}
		
		// compare password with confirm password
		
		// save to member to db
	}
}
