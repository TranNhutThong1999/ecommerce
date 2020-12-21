package com.tmdt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class Ward extends Common{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
