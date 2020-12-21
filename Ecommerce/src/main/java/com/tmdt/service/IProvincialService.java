package com.tmdt.service;

import java.util.List;
import java.util.Optional;

import com.tmdt.dto.ProvincialDTO;

public interface IProvincialService {
	List<ProvincialDTO> findAll();
}
