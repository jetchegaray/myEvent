package com.je.enterprise.mievento.domain.entity.wedding;

import com.je.enterprise.mievento.domain.entity.common.Location;
import com.je.enterprise.mievento.domain.entity.common.StreetAddress;

public class Present {

	private String type;
	private Boolean credit;
	private Location locationCredit;

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

	public Location getLocationCredit() {
		return locationCredit;
	}

	public void setLocationCredit(Location locationCredit) {
		this.locationCredit = locationCredit;
	}

}
