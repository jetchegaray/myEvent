package com.je.enterprise.mievento.domain.entity.location;


public class StreetAddressEntity {

	private String street;
	private String number;
	private String additionalInfo;
	private String neighborhood;

	
	public StreetAddressEntity() {
	}
	
	public StreetAddressEntity(String street, String number, String additionalInfo,
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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
