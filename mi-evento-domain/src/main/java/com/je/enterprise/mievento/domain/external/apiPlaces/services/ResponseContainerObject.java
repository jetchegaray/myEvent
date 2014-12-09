package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseContainerObject<Model> {

	private String status;
	@JsonProperty("result")
	private Model data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Model getData() {
		return data;
	}
	public void setData(Model data) {
		this.data = data;
	}
	
	
}
