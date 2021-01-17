package com.tmdt.entity;

import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee extends Common{
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
	public double getRaito() {
		return raito;
	}
	public void setRaito(double raito) {
		this.raito = raito;
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
