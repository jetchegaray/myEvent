package com.je.enterprise.mievento.domain.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("Users")
public class UserEntity extends BaseEntity {

	 @Indexed(dropDups = true, name = "userMailIndex", unique = true)
	private String mail;
	private String password;
	private boolean activate;
	
	
	public UserEntity(String mail, String password, boolean activate) {
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}


}
