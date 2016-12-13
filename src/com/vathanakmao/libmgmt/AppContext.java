package com.vathanakmao.libmgmt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.service.MemberService;

public class AppContext {
	private static AppContext instance;
	private Map<String, Object> components;
	private Properties properties;

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
		
		// Initialize properties
//		InputStream in = AppContext.class.getResourceAsStream("config/db.properties");
		InputStream in = null;
		try {
			in = AppContext.class.getResourceAsStream("/db-properties.xml");
			properties = new Properties();
			properties.loadFromXML(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
	
	public String getProperty(String name) {
		return (String) getInstance().getProperties().get(name);
	}
	
	public Properties getProperties() {
		return properties;
	}
}
