package com.je.enterprise.mievento.service.request;

import java.util.List;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;

public class ProviderTypesRequest {

	private List<ProviderType> types;
	private Location location;
	
	
	public ProviderTypesRequest() {
	}

	public ProviderTypesRequest(List<ProviderType> types,Location location) {
		this.types = types;
		this.location = location;
	}

	public List<ProviderType> getTypes() {
		return types;
	}

	public void setTypes(List<ProviderType> types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return types.toString();
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
}
