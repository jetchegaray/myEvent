package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;

public class Task {

	private String name;
	private Date initialDate;
	private Date finalDate;

	public Task() {
	}

	public Task(String name, Date initialDate, Date finalDate) {
		this.name = name;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
}
