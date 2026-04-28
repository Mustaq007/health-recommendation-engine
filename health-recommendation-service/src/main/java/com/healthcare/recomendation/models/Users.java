package com.healthcare.recomendation.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Users {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long  id;
	
	private String emailId;
	
	private String password;
	
	private String roles;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", emailId=" + emailId + ", password=" + password + ", roles=" + roles + "]";
	}
	
	
	
	
	
	

}
