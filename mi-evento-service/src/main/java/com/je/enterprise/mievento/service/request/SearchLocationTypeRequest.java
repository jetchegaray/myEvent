package com.je.enterprise.mievento.service.request;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;

public class SearchLocationTypeRequest {

	private ProviderType providerType;
	private Location location;
	
	public SearchLocationTypeRequest() {
	}

	public ProviderType getProviderType() {
		return providerType;
	}

	public void setProviderType(ProviderType type) {
		this.providerType = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("ProviderType : ").append(this.providerType);
		builder.append(" - Location : ").append(this.location);
		return builder.toString();
	}

	
}
