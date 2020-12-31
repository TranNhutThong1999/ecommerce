package com.tmdt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tmdt.dto.ActionDTO;

public interface IActionService {
	void save(ActionDTO a);

	Page<ActionDTO> findAllByUser_IdAndPost_State(Map<String, String> q);

	List<Integer> findListLike();
	
	void deletePostLike(ActionDTO a);
}
