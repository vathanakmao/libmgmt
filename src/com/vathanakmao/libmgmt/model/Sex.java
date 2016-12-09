package com.vathanakmao.libmgmt.model;

public enum Sex {
	MALE("M"), FEMALE("F");
	
	private String abbreviation;
	
	private Sex(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	/**
	 * Convert from string abbreviation ('F', 'M') to enum
	 * @param abbreviation
	 * @return
	 */
	public Sex from(String abbreviation) {
		for (Sex sex : Sex.values()) {
			if (sex.abbreviation.equals(abbreviation)) {
				return sex;
			}
		}
		throw new IllegalArgumentException(abbreviation + " is invalid");
	}

	public String toShortString() {
		return abbreviation;
	}
}
