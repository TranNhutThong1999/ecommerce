package com.tmdt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmdt.dto.DepositHistoryDTO;
import com.tmdt.entity.DepositHistory;

public class DepositHistoryConverter implements IConverter<DepositHistory, DepositHistoryDTO>{
	@Autowired
	private ModelMapper modelMapper;
	
	public DepositHistoryDTO toDTO(DepositHistory u) {
		DepositHistoryDTO d = modelMapper.map(u, DepositHistoryDTO.class);
		d.setIdUser(u.getUser().getId());
		return d;
	}

	public DepositHistory toEntity(DepositHistoryDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

}
