package com.tmdt.dto;

import java.security.Timestamp;

public class FeeDTO extends CommonDTO{
	private double raito;
	private String description;
	private String name;
	private int ranks;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	@Override
	public String toString() {
		return "FeeDTO [raito=" + raito + ", description=" + description + ", name=" + name + ", ranks=" + ranks + "]";
	}
	public double getRaito() {
		return raito;
	}
	public void setRaito(double raito) {
		this.raito = raito;
	}
	
}
