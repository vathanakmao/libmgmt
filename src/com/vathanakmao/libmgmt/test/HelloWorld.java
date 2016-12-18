package com.vathanakmao.libmgmt.test;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
//		String pattern = "!(^(/_admin/).*)";
		String pattern = "^((?!/_admin).)*$";
		
//		System.out.println("/_admin".matches(pattern));
		System.out.println("/_admin/".matches(pattern));
		System.out.println("/_admin/profile.jsp".matches(pattern));
//		System.out.println("/libmgmt/_admin".matches(pattern));
		System.out.println("/libmgmt/_admin/".matches(pattern));
		System.out.println("/libmgmt/_admin/profile.jsp".matches(pattern));

		System.out.println("----------------------------");
		
		
		System.out.println("/static".matches(pattern));
		System.out.println("/static/".matches(pattern));
		System.out.println("/static/css/index.css".matches(pattern));
		System.out.println("/_admin/logout".matches(pattern));
		System.out.println("/memberProfile.jsp".matches(pattern));
		System.out.println("/libmgmt/memberProfile.jsp".matches(pattern));
	}
}
