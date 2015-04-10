package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

public enum StatusResponse {

	OK("OK"), ZERO_RESULTS("ZERO_RESULTS"), OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"), 
	REQUEST_DENIED("REQUEST_DENIED"), INVALID_REQUEST("INVALID_REQUEST"), NOT_FOUND("NOT_FOUND");
	
	private StatusResponse(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}
	
}
