package com.je.enterprise.mievento.api.dto.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CountryCode {
	AR("ARGENTINA");
	
	private CountryCode(String name) {
		this.name = name;
	}
	
	private String name;

	@JsonValue
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static CountryCode getByName(String name){
		for(CountryCode country : values()){
			if(country.getName().equals(name)){
				return country;
			}
		}
		return null;
	}
}
