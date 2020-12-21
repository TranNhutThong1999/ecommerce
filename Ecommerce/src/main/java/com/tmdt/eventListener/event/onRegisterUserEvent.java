package com.tmdt.eventListener.event;

import org.springframework.context.ApplicationEvent;

import com.tmdt.dto.UserDTO;

public class onRegisterUserEvent extends ApplicationEvent{
	private UserDTO user;
	private String rootURL;
	public onRegisterUserEvent(Object source, UserDTO user, String rootURL) {
		super(source);
		this.user = user;
		this.rootURL = rootURL;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public String getRootURL() {
		return rootURL;
	}
	public void setRootURL(String rootURL) {
		this.rootURL = rootURL;
	}
	
	
}
