package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;

import com.je.enterprise.mievento.api.dto.event.StatusType;

public class InvitationStatusEntity {
	
	private StatusType status;
	private Date updateStatusDate;
	
	public InvitationStatusEntity() {
	}
	
	public InvitationStatusEntity(StatusType status, Date updateStatusDate) {
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
