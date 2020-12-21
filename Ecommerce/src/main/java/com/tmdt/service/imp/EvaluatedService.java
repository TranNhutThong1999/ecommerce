package com.tmdt.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.converter.EvaluatedConverter;
import com.tmdt.dto.EvaluatedDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.dto.StarDTO;
import com.tmdt.entity.Evaluated;
import com.tmdt.repository.EvaluatedRepository;
import com.tmdt.service.IEvaluatedService;

@Service
public class EvaluatedService implements IEvaluatedService {
	@Autowired
	private EvaluatedRepository evaluatedRepository;

	@Autowired
	private EvaluatedConverter evaluatedConverter;
	
	@Override
	public List<EvaluatedDTO> findByPost_Id(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(EvaluatedDTO e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(int star, int idPost) {
		// TODO Auto-generated method stub
		EvaluatedDTO e = new EvaluatedDTO();
		StarDTO t = new StarDTO();
		t.setId(star);
		
		e.setStar(t);
		e.setPostId(idPost);
		
		evaluatedRepository.save(evaluatedConverter.toEntity(e));
	}
	

}
