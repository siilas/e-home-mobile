package com.ehome.mobile.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Classe que representa a tabela LOGIN
 * 
 * @author Silas
 *
 */
public class Login implements Serializable {

	private static final long serialVersionUID = -1891654508769309812L;

	private Long id = 0L;
	private String username = "";
	private String password = "";
	
	public Login() {
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
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
		if ((this.id.equals(0L)) 
				&& (this.username.isEmpty())
				&& (this.password.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Login)) {
			return false;
		}
		
		Login other = (Login) object;
		
		return new EqualsBuilder()
				.append(id, other.id)
				.append(username, other.username)
				.append(password, other.password)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(username)
				.append(password)
				.toHashCode();
	}
}