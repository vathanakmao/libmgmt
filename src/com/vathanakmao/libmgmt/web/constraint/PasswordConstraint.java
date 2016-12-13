package com.vathanakmao.libmgmt.web.constraint;

public class PasswordConstraint extends ParamConstraint{
	private static final int MIN_CHARACTERS = 6;
	private static final String PATTERN_DIGITS_LETTERS = "([A-Za-z]+[0-9]+[A-Za-z]*)|([0-9]+[A-Za-z]+[0-9]*)";

	@Override
	public String check(String password) {
		if (password == null || password.length() < MIN_CHARACTERS) {
			return "Must be at least 6 characters";
		} else if (!password.matches(PATTERN_DIGITS_LETTERS)) {
			return "Must contain letters and numbers";
		}
		return null;
	}

}
