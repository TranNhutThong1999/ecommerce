package com.tmdt.eventListener.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import com.tmdt.eventListener.event.OnGetPostDetailEvent;
import com.tmdt.repository.ActionRepository;

public class Listener {
	@Autowired
	private ActionRepository actionRepository;
	
	@EventListener
	public void ViewPost(OnGetPostDetailEvent event) {
		
	}

}
