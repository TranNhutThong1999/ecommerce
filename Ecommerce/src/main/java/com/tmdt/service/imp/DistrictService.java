package com.tmdt.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.dto.DistrictDTO;
import com.tmdt.entity.District;
import com.tmdt.repository.DistrictRepository;
import com.tmdt.service.IDistrictService;

@Service
public class DistrictService implements IDistrictService {
	@Autowired
	private DistrictRepository districRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<DistrictDTO> findAll() {
		// TODO Auto-generated method stub
		List<District> l =districRepository.findAll();	
		return l.stream().map(e-> modelMapper.map(e, DistrictDTO.class)).collect(Collectors.toList());
	}

}
