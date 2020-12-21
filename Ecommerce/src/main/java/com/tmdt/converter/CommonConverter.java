package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmdt.dto.CommonDTO;
import com.tmdt.entity.Common;

public class CommonConverter implements IConverter<Common, CommonDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	public CommonDTO toDTO(Common u) {
		// TODO Auto-generated method stub
		return modelMapper.map(u, CommonDTO.class);
	}

	public Common toEntity(CommonDTO v) {
		// TODO Auto-generated method stub
		return modelMapper.map(v, Common.class);
	}

}
