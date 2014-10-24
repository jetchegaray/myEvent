package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class Photo {
	
	Integer height;
	Integer width;
	@JsonProperty("photo_reference")
	String reference;
	
	public Photo() {
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	
}
