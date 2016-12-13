package com.vathanakmao.libmgmt.web.constraint;

public class MandatoryConstraint extends ParamConstraint {
	private static final String ERROR_MESSAGE = "Required";

	@Override
	public String check(String value) {
		if (value == null || value.isEmpty()) {
			return ERROR_MESSAGE;
		}
		return null;
	}

}
