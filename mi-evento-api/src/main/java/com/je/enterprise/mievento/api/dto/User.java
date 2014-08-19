package com.je.enterprise.mievento.api.dto;


public class User {

	private String mail;
	private String password;
	private Boolean activate;

	public User() {
	}

	public User(String mail,String password,Boolean activate) {
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


}
