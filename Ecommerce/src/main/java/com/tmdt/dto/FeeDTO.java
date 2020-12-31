package com.tmdt.dto;


public class FeeDTO extends CommonDTO{
	private int price;
	private String description;
	private int expire;
	private String name;
	private int ranks;
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
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int rank) {
		this.ranks = rank;
	}
	
}
