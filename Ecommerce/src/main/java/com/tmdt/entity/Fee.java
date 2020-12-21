package com.tmdt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee extends Common{
	private int price;
	private String description;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
