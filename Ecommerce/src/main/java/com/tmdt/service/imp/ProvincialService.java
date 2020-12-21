package com.tmdt.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.dto.ProvincialDTO;
import com.tmdt.entity.Provincial;
import com.tmdt.repository.ProvincialRepository;
import com.tmdt.service.IProvincialService;

@Service
public class ProvincialService implements IProvincialService{
	@Autowired
	private ProvincialRepository provincialRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProvincialDTO> findAll() {
		// TODO Auto-generated method stub
		List<Provincial> l =provincialRepository.findAll();	
		return l.stream().map(e-> modelMapper.map(e, ProvincialDTO.class)).collect(Collectors.toList());
	}
	
}
