package com.tmdt.dto;

import com.tmdt.annotation.Address;

public class WardDTO extends CommonDTO{
	
	private String name;

	public String getName() {
		return name;
	}
	@Address(message = "Ward can not be null")
	public int getId() {
		return super.getId();
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "WardDTO [name=" + name + ", getName()=" + getName() + ", getId()=" + getId() + ", getCreatedDate()="
				+ getCreatedDate() + ", getCreatedBy()=" + getCreatedBy() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getModifedBy()=" + getModifedBy() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
