package com.tmdt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content")
public class Content extends Common{
	@Column(columnDefinition = "TEXT")
	private String description;
	private int price;
	private int areage;
	
	@Column(name = "electric_price")
	private int electricPrice;
	
	@Column(name = "water_price")
	private int waterPrice;
	
	@OneToOne(mappedBy = "content")
	private Post post;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAreage() {
		return areage;
	}

	public void setAreage(int areage) {
		this.areage = areage;
	}

	public int getElectricPrice() {
		return electricPrice;
	}

	public void setElectricPrice(int electricPrice) {
		this.electricPrice = electricPrice;
	}

	public int getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(int waterPrice) {
		this.waterPrice = waterPrice;
	}
	
	
	
}
