package com.je.enterprise.mievento.domain.entity.location;

import java.math.BigDecimal;


public class StreetAddressEntity {

	private String street;
	private BigDecimal number;
	private String additionalInfo;
	private String neighborhood;

	
	public StreetAddressEntity() {
	}
	
	public StreetAddressEntity(String street, BigDecimal number, String additionalInfo,
			String neighborhood) {
		this.street = street;
		this.number = number;
		this.additionalInfo = additionalInfo;
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
