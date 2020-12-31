package com.tmdt.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmdt.dto.UserDTO;

public class UpdateUser {
	private int id;
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String confirmPassword;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Email(message = "Wrong email ")
	private String email;
	
	@Size(min = 9,max = 11,message = "Between 9 and 11 characters")
	private String phone;
	
	private String address;

	public UpdateUser() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean confirmPassword() {
		return this.password.equals(this.confirmPassword);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public UserDTO convertDTO(UserDTO o) {
		o.setId(this.id);
		o.setName(this.name);
		o.setPhone(this.phone);
		o.setAddress(this.address);
		o.setEmail(this.email);
		if(!this.password.equals("")) {
			o.setPassword(this.password);
		}
		return o;
	}
}
