package com.tmdt.eventListener.listener;

import org.springframework.context.ApplicationListener;

import com.tmdt.eventListener.event.onRegisterUserEvent;

public class RegisterUserListener implements ApplicationListener<onRegisterUserEvent> {

	@Override
	public void onApplicationEvent(onRegisterUserEvent event) {
		// TODO Auto-generated method stub
		
	}

}
