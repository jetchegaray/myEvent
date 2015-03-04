package com.je.enterprise.mievento.api.dto.place;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.api.dto.provider.Review;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place extends Provider {

	private BigDecimal m2;
	private BigDecimal estimatedQuantityTables;
	private BigDecimal estimatedQuantityPerson;
	private ControlContextPlace controlContextPlace;
	
	public Place() {
		super();
	}

	public Place(String businessName, String description, Location location,
			String email, String cellPhone, String phone, BigDecimal price,
			BigDecimal estimatedPrice, BigDecimal m2,
			BigDecimal estimatedQuantityTables,
			BigDecimal estimatedQuantityPerson,List<String> photos,ProviderType providerType,List<Review> reviews) {
		super(businessName, description, location, email, cellPhone, phone,
				price, estimatedPrice,photos,providerType,reviews);
		this.m2 = m2;
		this.estimatedQuantityTables = estimatedQuantityTables;
		this.estimatedQuantityPerson = estimatedQuantityPerson;

	}

	public BigDecimal getM2() {
		return m2;
	}

	public void setM2(BigDecimal m2) {
		this.m2 = m2;
	}

	public BigDecimal getEstimatedQuantityTables() {
		return estimatedQuantityTables;
	}

	public void setEstimatedQuantityTables(BigDecimal estimatedQuantityTables) {
		this.estimatedQuantityTables = estimatedQuantityTables;
	}

	public BigDecimal getEstimatedQuantityPerson() {
		return estimatedQuantityPerson;
	}

	public void setEstimatedQuantityPerson(BigDecimal estimatedQuantityPerson) {
		this.estimatedQuantityPerson = estimatedQuantityPerson;
	}

	public ControlContextPlace getControlContextPlace() {
		return controlContextPlace;
	}

	public void setControlContextPlace(ControlContextPlace controlContextPlace) {
		this.controlContextPlace = controlContextPlace;
	}
}
