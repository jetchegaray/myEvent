package com.je.enterprise.mievento.domain.entity.wedding;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;

public class PresentEntity {

	private String type;
	private Boolean credit;
	@Embedded
	private CommercialLocationEntity locationCredit;

	public PresentEntity(String type, Boolean credit, CommercialLocationEntity locationCredit) {
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

	public CommercialLocationEntity getLocationCredit() {
		return locationCredit;
	}

	public void setLocationCredit(CommercialLocationEntity locationCredit) {
		this.locationCredit = locationCredit;
	}

}
