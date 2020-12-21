package com.tmdt.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.tmdt.dto.WardDTO;
import com.tmdt.entity.Ward;
import com.tmdt.repository.WardRepository;
import com.tmdt.service.IWardService;

@Service
public class WardService implements IWardService {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Override
	public List<WardDTO> findAll() {
		// TODO Auto-generated method stub
		List<Ward> l =wardRepository.findAll();	
		return l.stream().map(e-> modelMapper.map(e, WardDTO.class)).collect(Collectors.toList());
	}

}
