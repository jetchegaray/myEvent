package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;

public class InvitationStatus {

	private GuestStatusType status;
	private Date updateStatusDate;
	
	public InvitationStatus() {
	}
	
	public InvitationStatus(GuestStatusType status, Date updateStatusDate) {
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
