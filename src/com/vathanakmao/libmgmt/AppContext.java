package com.vathanakmao.libmgmt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vathanakmao.libmgmt.dao.BookDao;
import com.vathanakmao.libmgmt.dao.BorrowDao;
import com.vathanakmao.libmgmt.dao.LibrarianDao;
import com.vathanakmao.libmgmt.dao.MemberDao;
import com.vathanakmao.libmgmt.service.BookService;
import com.vathanakmao.libmgmt.service.BorrowService;
import com.vathanakmao.libmgmt.service.LibrarianService;
import com.vathanakmao.libmgmt.service.MemberService;

public class AppContext {
	private static AppContext instance;
	private Map<String, Object> components;
	private Properties properties;

	private MemberService memberService;
	private BookService bookService;
	private LibrarianService librarianService;
	private BorrowService borrowService;
	private MemberDao memberDao;
	private BookDao bookDao;
	private LibrarianDao librarianDao;
	private BorrowDao borrowDao;

	protected AppContext() {
		// Initialize DAOs
		memberDao = new MemberDao();
		bookDao = new BookDao();
		librarianDao = new LibrarianDao();
		borrowDao = new BorrowDao();

		// Initialize services
		memberService = new MemberService(memberDao);
		bookService = new BookService(bookDao);
		librarianService = new LibrarianService(librarianDao, memberDao, bookDao, borrowDao);
		borrowService = new BorrowService(borrowDao, bookDao);
		
		// Initialize components
		components = new HashMap<String, Object>();
		components.put("memberDao", memberDao);
		components.put("bookDao", bookDao);
		components.put("librarianDao", librarianDao);
		components.put("borrowDao", borrowDao);
		components.put("memberService", memberService);
		components.put("bookService", bookService);
		components.put("librarianService", librarianService);
		components.put("borrowService", borrowService);
		
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
