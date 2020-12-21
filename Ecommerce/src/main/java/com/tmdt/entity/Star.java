package com.tmdt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "star")
public class Star extends Common {
	private String name;
	private int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
