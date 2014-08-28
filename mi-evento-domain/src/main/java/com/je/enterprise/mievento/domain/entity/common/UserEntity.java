package com.je.enterprise.mievento.domain.entity.common;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("users")
public class UserEntity extends BaseEntity {

	@Indexed(dropDups = true, name = "userMailIndex", unique = true)
	private String email;
	private String password;
	private Boolean activate;
	@Embedded
	private List<Event> events;
	
	public UserEntity(String email, String password, boolean activate) {
		this.email = email;
		this.password = password;
		this.activate = activate;
	}

	public UserEntity() {
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

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
