package com.tmdt.dto;

public class EvaluatedDTO extends CommonDTO{
	private StarDTO star;
	
	//private UserDTO user;
	
	private int postId;

	public StarDTO getStar() {
		return star;
	}

	public void setStar(StarDTO star) {
		this.star = star;
	}
//
//	public UserDTO getUser() {
//		return user;
//	}
//
//	public void setUser(UserDTO user) {
//		this.user = user;
//	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	

	
}
