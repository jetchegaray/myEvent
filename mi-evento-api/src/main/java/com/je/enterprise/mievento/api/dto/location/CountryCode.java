package com.je.enterprise.mievento.api.dto.location;

public enum CountryCode {
	AR("ARGENTINA");
	
	private CountryCode(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}
}
