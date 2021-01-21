package com.tmdt.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.RoleDTO;
import com.tmdt.dto.UserDTO;
import com.tmdt.entity.Role;
import com.tmdt.entity.User;

@Component
public class UserConverter implements IConverter<User, UserDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostConverter postConveter;
	
	public UserDTO toDTO(User u) {
		// TODO Auto-generated method stub
		UserDTO user = modelMapper.map(u, UserDTO.class);
		user.setPosts(u.getPosts().stream().map(postConveter::toDTO).collect(Collectors.toList()));
		user.setRoles(u.getRoles().stream().map(a ->  modelMapper.map(a, RoleDTO.class)).collect(Collectors.toList()));
		return user;
	}

	public User toEntity(UserDTO v) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(v, User.class);
		if(v.getRoles() != null) {
			user.setRoles(v.getRoles().stream().map(a ->  modelMapper.map(a, Role.class)).collect(Collectors.toList()));
		}
		return user;
	}

}
