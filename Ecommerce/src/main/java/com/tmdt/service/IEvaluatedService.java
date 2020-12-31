package com.tmdt.service;

import java.util.List;

import com.tmdt.dto.EvaluatedDTO;

public interface IEvaluatedService {
	List<EvaluatedDTO> findByPost_Id( int id);
	void save(EvaluatedDTO e);
	boolean save(int star,int idPost);
}
