package com.tmdt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="deposit_history")
public class DepositHistory extends Common {
	private int money;
	private Date time;
	private int xu;
	private State state;
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public DepositHistory() {
		super();
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getXu() {
		return xu;
	}

	public void setXu(int xu) {
		this.xu = xu;
	}
	
}
