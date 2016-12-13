package com.vathanakmao.libmgmt;

import java.util.HashMap;
import java.util.Map;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.service.MemberService;

public class AppContext {
	private static AppContext instance;
	private Map<String, Object> components;

	private MemberService memberService;
	private MemberDao memberDao;

	protected AppContext() {
		// Initialize DAOs
		memberDao = new MemberDao();

		// Initialize services
		memberService = new MemberService();
		memberService.setMemberDao(memberDao);
		
		// Initialize components
		components = new HashMap<String, Object>();
		components.put("memberDao", memberDao);
		components.put("memberService", memberService);
	}

	public static AppContext getInstance() {
		if (instance == null) {
			instance = new AppContext();
		}
		return instance;
	}

	protected Map<String, Object> getComponents() {
		return components;
	}

	public Object getComponent(String name) {
		return getComponents().get(name);
	}
}
