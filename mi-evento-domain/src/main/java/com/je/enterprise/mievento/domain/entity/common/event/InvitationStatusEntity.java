package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;

import com.je.enterprise.mievento.api.dto.event.GuestStatusType;

public class InvitationStatusEntity {
	
	private GuestStatusType status;
	private Date updateStatusDate;
	
	public InvitationStatusEntity() {
	}
	
	public InvitationStatusEntity(GuestStatusType status, Date updateStatusDate) {
		this.status = status;
		this.updateStatusDate = updateStatusDate;
	}

	public GuestStatusType getStatus() {
		return status;
	}

	public void setStatus(GuestStatusType status) {
		this.status = status;
	}

	public Date getUpdateStatusDate() {
		return updateStatusDate;
	}

	public void setUpdateStatusDate(Date updateStatusDate) {
		this.updateStatusDate = updateStatusDate;
	}
}
