package com.home.mobile.model;

import java.io.Serializable;

public class Login implements Serializable {

	private static final long serialVersionUID = -1891654508769309812L;

	private Integer id = 0;
	private String username = "";
	private String password = "";
	
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
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public boolean isEmpty() {
		if ((this.id.equals(0)) || (this.username.isEmpty())
				|| (this.password.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
}