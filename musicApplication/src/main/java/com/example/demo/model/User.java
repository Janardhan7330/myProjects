package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="user name is required")
	@Size(min=4,max=15,message="user name must be between 3 - 15 characters")
	private String username;
	
	@NotBlank(message="password is required")
	@Size(min=6,message="password must be 6 characters")
	private String password;
	
	@NotBlank(message="password is required")
	@Size(min=6,message="password must be 6 characters")
	private String confirmpassword;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	public boolean ispasswordmatched() {
		return this.password.equals(this.confirmpassword);
	}
	
}
