package com.tmdt.dto;

import javax.validation.constraints.NotBlank;

public class ContentDTO extends CommonDTO{
	private String description;
	
	private int price;
	
	private int areage;
	
	private int electricPrice;
	
	private int waterPrice;
	
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

	@Override
	public String toString() {
		return "ContentDTO [description=" + description + ", price=" + price + ", areage=" + areage + ", electricPrice="
				+ electricPrice + ", waterPrice=" + waterPrice + "]";
	}
	
	
	
}
