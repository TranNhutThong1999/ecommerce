package com.tmdt.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.tmdt.annotation.Address;

public class AddressDTO extends CommonDTO {
	@NotBlank(message = "Username can not be blank")
	private String location;
	
	@Valid
	private WardDTO ward;
	
	@Valid
	private DistrictDTO district;
	
	@Valid
	private ProvincialDTO provincial;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public WardDTO getWard() {
		return ward;
	}

	public void setWard(WardDTO ward) {
		this.ward = ward;
	}

	public DistrictDTO getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDTO district) {
		this.district = district;
	}

	public ProvincialDTO getProvincial() {
		return provincial;
	}

	public void setProvincial(ProvincialDTO provincial) {
		this.provincial = provincial;
	}

	@Override
	public String toString() {
		return "AddressDTO [location=" + location + ", ward=" + ward + ", district=" + district + ", provincial="
				+ provincial + ", getLocation()=" + getLocation() + ", getWard()=" + getWard() + ", getDistrict()="
				+ getDistrict() + ", getProvincial()=" + getProvincial() + ", getId()=" + getId()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getModifedBy()=" + getModifedBy() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
