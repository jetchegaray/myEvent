package com.je.enterprise.mievento.api.dto.place;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ControlContextPlace {

	private List<ControlContextTable> controlContextTables;
	private ConfigurationPlace configurarionPlace;
	
	public ControlContextPlace() {
	}

	public ControlContextPlace(List<ControlContextTable> controlContextTables) {
		this.controlContextTables = controlContextTables;
	}

	public List<ControlContextTable> getControlContextTables() {
		return controlContextTables;
	}

	public void setControlContextTables(
			List<ControlContextTable> controlContextTables) {
		this.controlContextTables = controlContextTables;
	}
}
