package com.tmdt.dto;


public class ActionDTO extends CommonDTO {
	private String name;

	private UserDTO user;

	private PostDTO post;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
