package com.vathanakmao.libmgmt.test.util;

public class PasswordTest {

	public static void main(String[] args) {
		final String pattern = "([A-Za-z]+[0-9]+[A-Za-z]*)|([0-9]+[A-Za-z]+[0-9]*)";
		System.out.println("aa99".matches(pattern));
		System.out.println("aa99dd".matches(pattern));
		System.out.println("99aa".matches(pattern));
		System.out.println("99aa99".matches(pattern));
	}
}
