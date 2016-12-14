package com.vathanakmao.libmgmt.web.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vathanakmao.libmgmt.AppContext;
import com.vathanakmao.libmgmt.exception.NotFoundException;
import com.vathanakmao.libmgmt.model.Member;
import com.vathanakmao.libmgmt.service.MemberService;
import com.vathanakmao.libmgmt.util.WebUtil;
import com.vathanakmao.libmgmt.web.constraint.ParamValidator;
import com.vathanakmao.libmgmt.web.validator.LoginMemberValidator;

public class LoginMemberServlet extends HttpServlet {
	private MemberService service;
	
	public LoginMemberServlet() {
		service = (MemberService) AppContext.getInstance().getComponent("memberService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!new LoginMemberValidator().validate(req)) {
			WebUtil.forward("loginMember.jsp", req, resp);
		    return;
		}

		final String id = req.getParameter("id");
		final String plainPassword = req.getParameter("password");
		try {
			Member member = service.login(id, plainPassword);
			if (member != null) {
				WebUtil.createMemberSession(id, req);
				WebUtil.redirect("memberProfile.jsp", resp);
			} else {
				ParamValidator.addError("unknown", "Invalid Password" , req);
				WebUtil.forward("loginMember.jsp", req, resp);
			}
		} catch (NotFoundException e) {
			ParamValidator.addError("unknown", e.getMessage(), req);
			WebUtil.forward("loginMember.jsp", req, resp);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}
}
