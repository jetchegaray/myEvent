package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;

public class InvitationStatus {

	private StatusType status;
	private Date updateStatusDate;
	
	public InvitationStatus() {
	}
	
	public InvitationStatus(StatusType status, Date updateStatusDate) {
		this.status = status;
		this.updateStatusDate = updateStatusDate;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Date getUpdateStatusDate() {
		return updateStatusDate;
	}

	public void setUpdateStatusDate(Date updateStatusDate) {
		this.updateStatusDate = updateStatusDate;
	}
	
}
