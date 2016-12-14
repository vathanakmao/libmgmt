package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.AppContext;
import com.vathanakmao.libmgmt.exception.AlreadyExistsException;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.model.Sex;
import com.vathanakmao.libmgmt.service.MemberService;
import com.vathanakmao.libmgmt.util.WebUtil;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.validator.RegisterMemberValidator;

public class RegisterMemberServlet extends HttpServlet {
	private MemberService service;
	
	public RegisterMemberServlet() {
		service = (MemberService) AppContext.getInstance().getComponent("memberService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!new RegisterMemberValidator().validate(req)) {
			WebUtil.forward("registerMember.jsp", req, resp);
		    return;
		}

		Member member = new Member();
		member.setId(req.getParameter("id"));
		member.setFirstName(req.getParameter("firstName"));
		member.setLastName(req.getParameter("lastName"));
		member.setSex(Sex.fromString(req.getParameter("sex")));
		member.setAddress(req.getParameter("address"));
		member.setPassword(req.getParameter("password"));
		try {
			service.register(member);
			WebUtil.redirect("registrationSuccess.jsp", resp);
		} catch (AlreadyExistsException e) {
			ParamValidator.addError("unknown", e.getMessage(), req);
			WebUtil.forward("registerMember.jsp", req, resp);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}
}
