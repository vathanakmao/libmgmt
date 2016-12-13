package com.vathanakmao.libmgmt.web.constraint;

public class EqualConstraint extends ParamConstraint {
	private String target;
	private String defaultErrorMessage;

	public EqualConstraint(String target, String defaultErrorMessage) {
		this.target = target;
		this.defaultErrorMessage = defaultErrorMessage;
	}

	public String getTarget() {
		return target;
	}

	public String getDefaultErrorMessage() {
		return defaultErrorMessage;
	}

	@Override
	public String check(String source) {
		if (!source.equals(target)) {
			return getDefaultErrorMessage();
		}
		return null;
	}

}
