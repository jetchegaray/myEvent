package com.je.enterprise.mievento.api.dto.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CountryCode {
	AR("Argentina"),PY("Paraguay"),UY("Uruguay"),BR("Brazil"),EC("Ecuador"),VE("Venezuela"),
	CO("Colombia"),BO("Bolivia"),PE("Peru"),CL("Chile"),MX("Mexico"),CU("Cuba"),US("Estados Unidos"),ES("España");
	
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
