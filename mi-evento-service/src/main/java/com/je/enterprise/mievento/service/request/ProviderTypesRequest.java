package com.je.enterprise.mievento.service.request;

import java.util.List;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;

public class ProviderTypesRequest {

	List<ProviderType> types;
	
	public ProviderTypesRequest() {
	}

	public ProviderTypesRequest(List<ProviderType> types) {
		this.types = types;
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
	
}
