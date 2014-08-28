package com.je.enterprise.mievento.api.dto;


public class User {

	private String email;
	private String password;
	private Boolean activate;

	public User() {
	}

	public User(String email,String password,Boolean activate) {
		this.email = email;
		this.password = password;
		this.activate = activate;
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


}
