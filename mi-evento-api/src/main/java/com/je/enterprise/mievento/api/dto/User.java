package com.je.enterprise.mievento.api.dto;


public class User {

	private String mail;
	private String nickName;
	private String password;

	public User() {
	}

	public User(String mail, String nickName,String password) {
		this.mail = mail;
		this.nickName = nickName;
		this.password = password;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
