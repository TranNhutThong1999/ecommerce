package com.tmdt.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class TransactionHistoryDTO extends CommonDTO {
	private Date time;
	private FeeDTO fee;
	private UserDTO user;
	
	private PostDTO post;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public FeeDTO getFee() {
		return fee;
	}

	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}
	
}
