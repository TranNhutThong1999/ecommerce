package com.tmdt.service;

import java.util.List;
import java.util.Optional;

import com.tmdt.dto.WardDTO;

public interface IWardService {
	List<WardDTO> findAll();
}
