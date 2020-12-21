package com.tmdt.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class DepositHistoryDTO extends CommonDTO {
	private int money;
	private Date time;
	private State state;
	private String type;
	private int xu;
	
	private int idUser;

	public DepositHistoryDTO() {
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getXu() {
		return xu;
	}

	public void setXu(int xu) {
		this.xu = xu;
	}

	@Override
	public String toString() {
		return "DepositHistoryDTO [money=" + money + ", time=" + time + ", state=" + state + ", type=" + type
				+ ", idUser=" + idUser + "]";
	}

	
}
