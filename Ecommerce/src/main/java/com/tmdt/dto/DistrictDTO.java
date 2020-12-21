package com.tmdt.dto;

import com.tmdt.annotation.Address;

public class DistrictDTO extends CommonDTO{
	private String name;
	
	@Address(message = "District can not be null")
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
