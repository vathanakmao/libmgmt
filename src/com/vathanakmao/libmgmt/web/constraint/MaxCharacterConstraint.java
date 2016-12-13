package com.vathanakmao.libmgmt.web.constraint;

public class MaxCharacterConstraint extends ParamConstraint {
	private static final String ERROR_MESSAGE = "Cannot be longer than %s characters";
	
	private int range;
	
	public MaxCharacterConstraint(int range) {
		this.range = range;
	}

	@Override
	public String check(String value) {
		if (value != null && value.length() > range) {
			return String.format(ERROR_MESSAGE, range);
		}
		return null;
	}

}
