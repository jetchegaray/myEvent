package com.je.enterprise.mievento.api.dto.event.eventWithplace;

import com.je.enterprise.mievento.api.dto.location.CommercialLocation;

public class Present {

	private String type;
	private Boolean credit;
	private CommercialLocation locationCredit;

	public Present(String type, Boolean credit, CommercialLocation locationCredit) {
		this.type = type;
		this.credit = credit;
		this.locationCredit = locationCredit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getCredit() {
		return credit;
	}

	public void setCredit(Boolean credit) {
		this.credit = credit;
	}

	public CommercialLocation getLocationCredit() {
		return locationCredit;
	}

	public void setLocationCredit(CommercialLocation locationCredit) {
		this.locationCredit = locationCredit;
	}

}
