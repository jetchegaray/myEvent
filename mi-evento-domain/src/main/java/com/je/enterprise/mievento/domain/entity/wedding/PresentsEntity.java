package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;

public class PresentsEntity {
	
	@Embedded
	private List<String> description;
	private BigDecimal totalBudget;
	@Embedded
	private CommercialLocationEntity locationCredit;
	@Embedded
	private List<GuestEntity> guestsPay;

	public PresentsEntity(List<String> description, BigDecimal totalBudget,
			CommercialLocationEntity locationCredit, List<GuestEntity> guestsPay) {
		this.description = description;
		this.totalBudget = totalBudget;
		this.locationCredit = locationCredit;
		this.guestsPay = guestsPay;
	}

	public PresentsEntity() {
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public BigDecimal getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(BigDecimal totalBudget) {
		this.totalBudget = totalBudget;
	}

	public CommercialLocationEntity getLocationCredit() {
		return locationCredit;
	}

	public void setLocationCredit(CommercialLocationEntity locationCredit) {
		this.locationCredit = locationCredit;
	}

	public List<GuestEntity> getGuestsPay() {
		return guestsPay;
	}

	public void setGuestsPay(List<GuestEntity> guestsPay) {
		this.guestsPay = guestsPay;
	}
}
