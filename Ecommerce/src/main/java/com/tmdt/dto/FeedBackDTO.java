package com.tmdt.dto;

import java.sql.Timestamp;
import java.util.Date;

public class FeedBackDTO extends CommonDTO{
	private StarDTO star;
	private String content;
	private int idUser;
	private long time;
	private int idPost;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public StarDTO getStar() {
		return star;
	}

	public void setStar(StarDTO star) {
		this.star = star;
	}
	
}
