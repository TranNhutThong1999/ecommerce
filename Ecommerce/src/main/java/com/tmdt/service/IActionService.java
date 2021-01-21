package com.tmdt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tmdt.dto.ActionDTO;
import com.tmdt.dto.PostDTO;

public interface IActionService {
	void save(ActionDTO a);

	Page<PostDTO> findAllByUser_IdAndPost_State(Map<String, String> q);

	List<Integer> findListLike();
	
	void deletePostLike(ActionDTO a);
}
