package com.home.mobile.model;

import java.io.Serializable;

public class Configurations implements Serializable {

	private static final long serialVersionUID = 7828750693142424179L;
	
	private Integer id = 0;
	private String username = "";
	private String email = "";
	private String url = "";
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isEmpty() {
		if ((this.id.equals(0)) && (this.username.isEmpty()) 
				&& (this.email.isEmpty()) && (this.url.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
}