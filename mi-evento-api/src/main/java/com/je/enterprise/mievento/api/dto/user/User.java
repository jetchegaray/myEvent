package com.je.enterprise.mievento.api.dto.user;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;


public class User {

	private String email;
	private String password;
	private Boolean activate;
	private List<Event> events;
	
	public User() {
	}

	public User(String email,String password,Boolean activate,List<Event> events) {
		this.email = email;
		this.password = password;
		this.activate = activate;
		this.events = events;
	}

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}


}
