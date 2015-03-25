package com.je.enterprise.mievento.api.dto.event.eventWithplace;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Presents {

	private List<String> descriptions;
	private BigDecimal totalBudget;
	private CommercialLocation locationCredit;
	private List<Guest> guestsPay;

	public Presents(List<String> description, BigDecimal totalBudget,
			CommercialLocation locationCredit, List<Guest> guestsPay) {
		this.descriptions = description;
		this.totalBudget = totalBudget;
		this.locationCredit = locationCredit;
		this.guestsPay = guestsPay;
	}

	public Presents() {
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> description) {
		this.descriptions = description;
	}

	public BigDecimal getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(BigDecimal totalBudget) {
		this.totalBudget = totalBudget;
	}

	public CommercialLocation getLocationCredit() {
		return locationCredit;
	}

	public void setLocationCredit(CommercialLocation locationCredit) {
		this.locationCredit = locationCredit;
	}

	public List<Guest> getGuestsPay() {
		return guestsPay;
	}

	public void setGuestsPay(List<Guest> guestsPay) {
		this.guestsPay = guestsPay;
	}

}
