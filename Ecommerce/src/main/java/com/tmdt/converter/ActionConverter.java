package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.ActionDTO;
import com.tmdt.entity.Action;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;

@Component
public class ActionConverter implements IConverter<Action, ActionDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private CustomUserDetail customUserDetal;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostConverter postConverter;
	
	@Override
	public ActionDTO toDTO(Action u) {
		// TODO Auto-generated method stub
		ActionDTO a = modelMapper.map(u, ActionDTO.class);
		a.setUser(userConverter.toDTO(u.getUser()));
		a.setPost(postConverter.toDTO(u.getPost()));
		return a;
	}

	@Override
	public Action toEntity(ActionDTO v) {
		// TODO Auto-generated method stub
		Action a = modelMapper.map(v, Action.class);
		a.setUser(userRepository.findOneByUserName(customUserDetal.getPrinciple().getName()).get());
		a.setPost(postRepository.findOneById(v.getPost().getId()).get());
		return a;
	}
		
}
