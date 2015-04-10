package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("users")
public class UserEntity extends BaseEntity {

	@Indexed(dropDups = true, name = "userMailIndex", unique = true)
	private String nickName;
	private String email;
	private String password;
	private Boolean activate;
	@Embedded
	private List<EventEntity> events = Lists.<EventEntity>newArrayList();
	
	public UserEntity() {
	}
	
	public UserEntity(String nickName,String email, String password, boolean activate) {
		this.nickName = nickName;
		this.email = email;
		this.password = password;
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

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public List<EventEntity> getEvents() {
		return events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
