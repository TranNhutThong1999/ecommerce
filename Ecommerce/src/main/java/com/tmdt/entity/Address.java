package com.tmdt.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends Common {
	private String location;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private Ward ward;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provincial_id")
	private Provincial provincial;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Provincial getProvincial() {
		return provincial;
	}

	public void setProvincial(Provincial provincial) {
		this.provincial = provincial;
	}
	
	
}
