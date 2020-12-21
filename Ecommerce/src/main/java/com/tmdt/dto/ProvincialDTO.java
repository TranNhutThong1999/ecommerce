package com.tmdt.dto;

import com.tmdt.annotation.Address;

public class ProvincialDTO extends CommonDTO {
	private String name;

	@Address(message = "Provincial can not be null")
	public int getId() {
		return super.getId();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
