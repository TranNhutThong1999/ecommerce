package com.tmdt.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tmdt.dto.StarDTO;
import com.tmdt.repository.StarRepository;
import com.tmdt.service.IStarService;


@Service
public class StarService implements IStarService{
	@Autowired
	private StarRepository starRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<StarDTO> findAll() {
		return starRepository.findAll(Sort.by(Sort.Direction.DESC, "value")).stream().map(e ->modelMapper.map(e, StarDTO.class)).collect(Collectors.toList());
	}
	
}
