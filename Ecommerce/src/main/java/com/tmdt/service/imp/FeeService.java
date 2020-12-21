package com.tmdt.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.dto.FeeDTO;
import com.tmdt.entity.Fee;
import com.tmdt.repository.FeeRepository;
import com.tmdt.service.IFeeService;

@Service
public class FeeService implements IFeeService{
	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<FeeDTO> findAll() {
		// TODO Auto-generated method stub
		List<Fee> l =feeRepository.findAll();	
		return l.stream().map(e-> modelMapper.map(e, FeeDTO.class)).collect(Collectors.toList());
	}
	
}
