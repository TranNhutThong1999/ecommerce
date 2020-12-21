package com.tmdt.dto;


public class FeedBackDTO extends CommonDTO{
	private String content;
	private UserDTO user;
	
	private PostDTO post;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
