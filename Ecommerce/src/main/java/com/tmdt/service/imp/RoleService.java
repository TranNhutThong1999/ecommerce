package com.tmdt.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.entity.Role;
import com.tmdt.repository.RoleRepository;
import com.tmdt.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleRepository roleRepository;
	

}
