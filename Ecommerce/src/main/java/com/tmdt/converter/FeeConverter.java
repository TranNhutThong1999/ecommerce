package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmdt.dto.FeeDTO;
import com.tmdt.entity.Fee;

@Component
public class FeeConverter implements IConverter<Fee, FeeDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	public FeeDTO toDTO(Fee u) {
		// TODO Auto-generated method stub
		return modelMapper.map(u, FeeDTO.class);
	}

	public Fee toEntity(FeeDTO v) {
		// TODO Auto-generated method stub
		return modelMapper.map(v, Fee.class);
	}

}
