package com.je.enterprise.mievento.api.dto.country;

import java.util.List;

import com.je.enterprise.mievento.api.dto.location.CountryCode;

public class Country {

	private String name;
	private CountryCode code;
	private List<State> states;
	
	public Country(String name, CountryCode code, List<State> states) {
		this.name = name;
		this.code = code;
		this.states = states;
	}

	public Country() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}


	public CountryCode getCode() {
		return code;
	}


	public void setCode(CountryCode code) {
		this.code = code;
	}
	
	
}
