package com.tmdt.eventListener.event;

import org.springframework.context.ApplicationEvent;
import com.tmdt.dto.PostDTO;

public class OnGetPostDetailEvent extends ApplicationEvent{
	private PostDTO post;

	public OnGetPostDetailEvent(Object source, PostDTO post) {
		super(source);
		this.post = post;
	}

	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}
	
	
}
